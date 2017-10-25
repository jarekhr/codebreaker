package com.jahs.codebreaker.game.ai;

import com.google.common.collect.*;
import com.jahs.codebreaker.model.GameConfig;
import com.jahs.codebreaker.model.PinColor;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Immutable class which represents a state in result resolution process.
 *
 */
public class ResolutionContext {

    private final GameConfig gameConfig;
    private final List<FieldConfig> fields;

    private final Set<PinColor> colorsRequiredButNotAllocated;

    private ResolutionContext(GameConfig gameConfig,
                              List<FieldConfig> fieldConfigs,
                              Set<PinColor> colorsRequiredButNotAllocated) {
        this.gameConfig = Objects.requireNonNull(gameConfig);
        if (fieldConfigs.size() != gameConfig.getCodeLength()) {
            throw new IllegalArgumentException("Expected " + fieldConfigs.size()
                    + " configs, got: " + fieldConfigs.size());
        }

        // TODO jarek we should try and allocate required colors if possible.
//        this.fields = tryToAllocateRequired(fieldConfigs)

        this.fields = ImmutableList.copyOf(fieldConfigs);
        this.colorsRequiredButNotAllocated = EnumSet.copyOf(colorsRequiredButNotAllocated);
    }


    public static class FieldConfig {

        private final Set<PinColor> allowedColors;

        public FieldConfig(Set<PinColor> allowedColors) {
            this.allowedColors = EnumSet.copyOf(allowedColors);
        }

        public boolean isSelected() {
            return allowedColors.size() == 1;
        }

        public PinColor getSelectedColor() {
            return Iterables.getOnlyElement(allowedColors);
        }

        public Set<PinColor> getAllowedColors() {
            return allowedColors;
        }

        public FieldConfig withoutColor(PinColor color) {
            if (allowedColors.contains(color)) {
                return new FieldConfig(Sets.difference(allowedColors, EnumSet.of(color)));
            }
            return this;
        }

        public FieldConfig selectColor(PinColor color) {
            if (allowedColors.contains(color)) {
                return new FieldConfig(EnumSet.of(color));
            }
            throw new IllegalArgumentException("Requested to remove color " + color + ", but it's not here, we only got " + allowedColors);
        }

        public boolean isFailed() {
            return allowedColors.isEmpty();
        }
    }


    public static ResolutionContext emptyContext(GameConfig gameConfig) {
        //none of the colors is selected, and any color can be assigned to any of the fields.

        FieldConfig fieldConfig = new FieldConfig(ImmutableSet.copyOf(PinColor.values()));
        ImmutableList.Builder<FieldConfig> configList = ImmutableList.builder();
        gameConfig.getIndexesStream().forEach(i -> configList.add(fieldConfig));
        return new ResolutionContext(gameConfig, configList.build(), Collections.emptySet());
    }


    public FieldConfig getFieldConfig(int i) {
        return fields.get(i);
    }


    public ResolutionContext selectColor(int index, PinColor color) {
        List<FieldConfig> newConfigs = gameConfig.getIndexesStream().mapToObj(i -> {
            FieldConfig fieldConfig = getFieldConfig(i);
            if (i == index) {
                fieldConfig = fieldConfig.selectColor(color);
            } else {
                fieldConfig = fieldConfig.withoutColor(color);
            }
            return fieldConfig;
        }).collect(Collectors.toList());
        return new ResolutionContext(gameConfig, newConfigs, colorsRequiredButNotAllocated);
    }

    public ResolutionContext excludeColor(PinColor color) {
        List<FieldConfig> newConfigs = fields.stream()
                .map(fieldConfig -> fieldConfig.withoutColor(color)).collect(Collectors.toList());
        return new ResolutionContext(gameConfig, newConfigs, colorsRequiredButNotAllocated);
    }


    public ResolutionContext requireElsewhere(int index, PinColor color) {
        List<FieldConfig> newConfigs = gameConfig.getIndexesStream().mapToObj(i -> {
            FieldConfig config = getFieldConfig(i);
            if (i == index) {
                config = config.withoutColor(color);
            }
            return config;
        }).collect(Collectors.toList());
        Set<PinColor> requiredElsewhere = ImmutableSet.<PinColor>builder()
                .addAll(colorsRequiredButNotAllocated)
                .add(color)
                .build();
        return new ResolutionContext(gameConfig, newConfigs, requiredElsewhere);
    }
}

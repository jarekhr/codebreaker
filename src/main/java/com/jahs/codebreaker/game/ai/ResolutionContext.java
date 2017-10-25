package com.jahs.codebreaker.game.ai;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.jahs.codebreaker.model.GameConfig;
import com.jahs.codebreaker.model.PinColor;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ResolutionContext {

    private final GameConfig gameConfig;
    private final List<FieldConfig> fields;

    private ResolutionContext(GameConfig gameConfig, List<FieldConfig> fieldConfigs) {
        this.gameConfig = Objects.requireNonNull(gameConfig);
        if (fieldConfigs.size() != gameConfig.getCodeLength()) {
            throw new IllegalArgumentException("Expected " + fieldConfigs.size()
                    + " configs, got: " + fieldConfigs.size());
        }
        this.fields = ImmutableList.copyOf(fieldConfigs);
    }


    public static class FieldConfig {

        private final Set<PinColor> allowedColors;

        public FieldConfig(Set<PinColor> allowedColors) {
            this.allowedColors = ImmutableSet.copyOf(allowedColors);
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
    }


    public static ResolutionContext emptyContext(GameConfig gameConfig) {
        //none of the colors is selected, and any color can be assigned to any of the fields.

        FieldConfig fieldConfig = new FieldConfig(ImmutableSet.copyOf(PinColor.values()));
        ImmutableList.Builder<FieldConfig> configList = ImmutableList.builder();
        gameConfig.getIndexesStream().forEach(i -> configList.add(fieldConfig));
        return new ResolutionContext(gameConfig, configList.build());
    }


    public FieldConfig getFieldConfig(int i) {
        return fields.get(i);
    }
}

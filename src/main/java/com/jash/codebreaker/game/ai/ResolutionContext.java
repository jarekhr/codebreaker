package com.jash.codebreaker.game.ai;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
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
        private final PinColor selectedColor;

        public FieldConfig(Set<PinColor> allowedColors, PinColor selectedColor) {
            this.allowedColors = ImmutableSet.copyOf(allowedColors);
            this.selectedColor = selectedColor;
        }

        public boolean isSelected() {
            return selectedColor != null;
        }

        public PinColor getSelectedColor() {
            return selectedColor;
        }
    }


    public static ResolutionContext emptyContext(GameConfig gameConfig) {
        //none of the colors is selected, and any color can be assigned to any of the fields.

        FieldConfig fieldConfig = new FieldConfig(ImmutableSet.copyOf(PinColor.values()), null);
        ImmutableList.Builder<FieldConfig> configList = ImmutableList.builder();
        for (int i = 0; i < gameConfig.getCodeLength(); i++) {
            configList.add(fieldConfig);
        }
        return new ResolutionContext(gameConfig, configList.build());
    }


    public FieldConfig getFieldConfig(int i) {
        return fields.get(i);
    }
}

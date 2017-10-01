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


    private static class FieldConfig {

        private final Set<PinColor> allowedColors;
        private final PinColor selectedColor;

        public FieldConfig(Set<PinColor> allowedColors, PinColor selectedColor) {
            this.allowedColors = ImmutableSet.copyOf(allowedColors);
            this.selectedColor = selectedColor;
        }

        public boolean isSelected() {
            return selectedColor != null;
        }
    }

}

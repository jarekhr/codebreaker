package com.jahs.codebreaker.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;

/**
 * Code is a fixed length sequence of colors.
 */
public class Code {

    private final List<PinColor> pins;
    private final Map<PinColor, Integer> colorToIndex;

    public Code(GameConfig gameConfig, List<PinColor> pins) {
        // make sure we get all the pins..
        if (pins.size() != gameConfig.getCodeLength()) {
            throw new IllegalArgumentException("Expected " + gameConfig.getCodeLength()
                    + " pins, but got " + pins);
        }
        ImmutableMap.Builder<PinColor, Integer> indexBuilder = ImmutableMap.builder();
        for (int i = 0; i < gameConfig.getCodeLength(); i++) {
            indexBuilder.put(pins.get(i), i);
        }
        this.colorToIndex = indexBuilder.build();
        if (colorToIndex.keySet().size() != gameConfig.getCodeLength()) {
            throw new IllegalArgumentException("Expected all unique pins, got: " + pins);
        }
        this.pins = ImmutableList.copyOf(pins);
    }

    public List<PinColor> getPins() {
        return pins;
    }

    public PinColor getPinAtPosition(int positionIndex) {
        if (positionIndex >= pins.size() -1) {
            throw new IllegalArgumentException("Index out of range: " + positionIndex);
        }
        return pins.get(positionIndex);
    }

    public boolean hasColor(PinColor color) {
        return colorToIndex.keySet().contains(color);
    }

    public int getColorPosition(PinColor color) {
        Integer index = colorToIndex.get(color);
        if (index == null) {
            return -1;
        }
        return index;
    }

    @Override
    public String toString() {
        return "Code{" +
                "pins=" + pins +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Code code = (Code) o;

        return getPins() != null ? getPins().equals(code.getPins()) : code.getPins() == null;
    }

    @Override
    public int hashCode() {
        return getPins() != null ? getPins().hashCode() : 0;
    }
}

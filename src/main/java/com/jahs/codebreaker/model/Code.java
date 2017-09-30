package com.jahs.codebreaker.model;

import com.google.common.collect.ImmutableList;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Code is a fixed length sequence of colors.
 */
public class Code {

    private final List<PinColor> pins;
    private final Set<PinColor> pinsSet;

    public Code(GameConfig gameConfig, List<PinColor> pins) {
        // make sure we get all the pins..
        if (pins.size() != GameConstants.CODE_LENGTH) {
            throw new IllegalArgumentException("Expected " + GameConstants.CODE_LENGTH
                    + " pins, but got " + pins);
        }
        pinsSet = EnumSet.copyOf(pins.stream().collect(Collectors.toSet()));
        if (pinsSet.size() != GameConstants.CODE_LENGTH) {
            throw new IllegalArgumentException("Expected all unique pins, got: " + pins);
        }

        this.pins = ImmutableList.copyOf(pins);
    }

    public List<PinColor> getPins() {
        return pins;
    }

    public PinColor getPinAtPosition(int positionIndex) {
        if (positionIndex >= GameConstants.CODE_LENGTH) {
            throw new IllegalArgumentException("Index out of range: " + positionIndex);
        }
        return pins.get(positionIndex);
    }

    public boolean hasColor(PinColor color) {
        return pinsSet.contains(color);
    }

}

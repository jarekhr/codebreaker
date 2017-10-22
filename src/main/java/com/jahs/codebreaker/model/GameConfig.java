package com.jahs.codebreaker.model;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameConfig {

    private final int codeLength;

    public GameConfig(int codeLength) {
        this.codeLength = codeLength;
    }

    public int getCodeLength() {
        return codeLength;
    }

    public Set<Integer> getAllIndexes() {
        return getIndexesStream().boxed().collect(Collectors.toSet());
    }

    public IntStream getIndexesStream() {
        return IntStream.range(0, codeLength);
    }
}

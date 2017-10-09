package com.jahs.codebreaker.model;

import com.google.common.collect.Sets;

import java.util.Set;

public class GameConfig {

    private final int codeLength;

    public GameConfig(int codeLength) {
        this.codeLength = codeLength;
    }

    public int getCodeLength() {
        return codeLength;
    }

    public Set<Integer> getAllIndexes() {
        Set<Integer> indexes = Sets.newHashSet();
        for (int i = 0; i < codeLength; i++) {
            indexes.add(i);
        }
        return indexes;
    }
}

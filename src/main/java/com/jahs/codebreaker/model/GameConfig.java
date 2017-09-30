package com.jahs.codebreaker.model;

public class GameConfig {

    private final int codeLength;

    public GameConfig(int codeLength) {
        this.codeLength = codeLength;
    }

    public int getCodeLength() {
        return codeLength;
    }

}

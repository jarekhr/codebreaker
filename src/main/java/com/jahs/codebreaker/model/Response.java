package com.jahs.codebreaker.model;

/**
 * Represents a response to a code.
 * White pin represents a color which exists in the code, but at a different index.
 * Black pin is an exact match.
 *
 */
public class Response {

    private final GameConfig gameConfig;

    private final int whitePins;
    private final int blackPins;

    public Response(GameConfig gameConfig, int whitePins, int blackPins) {
        int total = whitePins + blackPins;
        if (total > gameConfig.getCodeLength()) {
            throw new IllegalArgumentException("Expected max " + gameConfig.getCodeLength()
                    + " response pins. Got " + total);
        }
        this.whitePins = whitePins;
        this.blackPins = blackPins;
        this.gameConfig = gameConfig;
    }

    public int getWhitePins() {
        return whitePins;
    }

    public int getBlackPins() {
        return blackPins;
    }

    public boolean isSuccess() {
        return blackPins == gameConfig.getCodeLength();
    }
}

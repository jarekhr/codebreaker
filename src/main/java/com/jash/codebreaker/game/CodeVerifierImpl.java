package com.jash.codebreaker.game;

import com.jahs.codebreaker.model.*;

import java.util.Objects;

/**
 * Default code verifier.
 *
 */
public class CodeVerifierImpl implements CodeVerifier {

    private final GameConfig gameConfig;

    public CodeVerifierImpl(GameConfig gameConfig) {
        this.gameConfig = Objects.requireNonNull(gameConfig);
    }

    @Override
    public Response verifyCode(Code secret, Code guess) {
        int whitePins = 0;
        int blackPins = 0;
        for (int i = 0; i < gameConfig.getCodeLength(); i++) {
            PinColor expected = secret.getPinAtPosition(i);
            PinColor actual = guess.getPinAtPosition(i);
            if (expected == actual) {
                blackPins++;
            } else {
                if (secret.hasColor(actual)) {
                    whitePins++;
                }
            }
        }
        return new Response(gameConfig, whitePins, blackPins);
    }

}

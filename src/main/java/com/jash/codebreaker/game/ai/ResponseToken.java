package com.jash.codebreaker.game.ai;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.jahs.codebreaker.model.Code;
import com.jahs.codebreaker.model.GameConfig;
import com.jahs.codebreaker.model.PinColor;
import com.jahs.codebreaker.model.Response;
import com.jash.codebreaker.game.CodeVerifier;

import java.util.List;
import java.util.Objects;

public enum ResponseToken {
    HIT, // precise guess
    MISPLACED, // color is there, but in a different place
    MISS // color is not there!
    ;


    public static List<ResponseToken> getTokensFromResponse(Response response) {
        List<ResponseToken> result = Lists.newArrayList();
        addTokens(result, response.getBlackPins(), ResponseToken.HIT);
        addTokens(result, response.getWhitePins(), ResponseToken.MISPLACED);
        addTokens(result, response.getMisses(), ResponseToken.MISS);
        return ImmutableList.copyOf(result);
    }

    private static void addTokens(List<ResponseToken> target, int count, ResponseToken token) {
        for (int i = 0; i < count; i++) {
            target.add(token);
        }
    }

    /**
     * Default code verifier.
     *
     */
    public static class CodeVerifierImpl implements CodeVerifier {

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
}

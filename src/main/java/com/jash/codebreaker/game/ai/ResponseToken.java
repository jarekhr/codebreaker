package com.jash.codebreaker.game.ai;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.jahs.codebreaker.model.Response;

import java.util.List;

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
}

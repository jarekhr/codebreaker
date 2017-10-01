package com.jash.codebreaker.game.ai;

import com.jahs.codebreaker.model.GameConfig;
import com.jahs.codebreaker.model.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ResponseTokenTest {

    private final GameConfig gameConfig = new GameConfig(4);

    @Test
    public void testGetTokens() {
        List<ResponseToken> tokens = ResponseToken.getTokensFromResponse(new Response(gameConfig, 1, 2));
        Assert.assertEquals(tokens.size(), gameConfig.getCodeLength());
        Assert.assertEquals(Arrays.asList(ResponseToken.HIT, ResponseToken.HIT, ResponseToken.MISPLACED, ResponseToken.MISS),
                tokens);
    }

}
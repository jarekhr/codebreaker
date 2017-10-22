package com.jahs.codebreaker.game;

import com.jahs.codebreaker.model.Code;
import com.jahs.codebreaker.model.GameConfig;
import com.jahs.codebreaker.model.PinColor;
import com.jahs.codebreaker.model.Response;
import com.jahs.codebreaker.game.ai.ResponseToken;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class CodeVerifierImplTest {

    private GameConfig gameConfig = new GameConfig(5);
    private ResponseToken.CodeVerifierImpl impl;

    @Before
    public void configure() {
        impl = new ResponseToken.CodeVerifierImpl(gameConfig);
    }

    @Test
    public void testVerify() {
        Code secret = new Code(gameConfig, Arrays.asList(PinColor.ORANGE, PinColor.PINK, PinColor.WHITE, PinColor.BLACK, PinColor.RED));
        Code guess = new Code(gameConfig, Arrays.asList(PinColor.ORANGE, PinColor.WHITE, PinColor.PINK, PinColor.BLACK, PinColor.GREEN));

        Response response = impl.verifyCode(secret, guess);
        Assert.assertEquals(2, response.getBlackPins());
        Assert.assertEquals(2, response.getWhitePins());

    }

}
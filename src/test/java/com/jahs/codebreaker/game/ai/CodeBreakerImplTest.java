package com.jahs.codebreaker.game.ai;

import com.jahs.codebreaker.model.Code;
import com.jahs.codebreaker.model.GameConfig;
import com.jahs.codebreaker.model.GuessHistory;
import com.jahs.codebreaker.model.PinColor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class CodeBreakerImplTest {

    private final GameConfig gameConfig = new GameConfig(3);
    private CodeBreakerImpl impl;

    @Before
    public void configure() {
        this.impl = new CodeBreakerImpl(gameConfig);
    }

    @Test
    public void testInitialGuess() {
        Code code = impl.attemptGuess(new GuessHistory(Collections.emptyList()));
        Code expectedCode = new Code(gameConfig, Arrays.asList(PinColor.RED, PinColor.ORANGE, PinColor.YELLOW));
        Assert.assertEquals(expectedCode, code);
    }

}
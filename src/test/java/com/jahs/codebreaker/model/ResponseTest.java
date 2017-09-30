package com.jahs.codebreaker.model;

import org.junit.Assert;
import org.junit.Test;

public class ResponseTest {

    private GameConfig gameConfig = new GameConfig(2);

    @Test(expected = IllegalArgumentException.class)
    public void testTooManyPins() {
        new Response(gameConfig, 2, 1);
    }

    @Test
    public void testSuccess() {
        Assert.assertFalse(new Response(gameConfig, 0, 0).isSuccess());
        Assert.assertFalse(new Response(gameConfig, 2, 0).isSuccess());
        Assert.assertTrue(new Response(gameConfig,0, 2).isSuccess());
    }

}
package com.jahs.codebreaker.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CodeTest {

    private GameConfig gameConfig = new GameConfig(5);

    @Test(expected = IllegalArgumentException.class)
    public void testFailsOnTooShort() {
        new Code(gameConfig, Arrays.asList(Arrays.copyOfRange(PinColor.values(), 0, gameConfig.getCodeLength() - 2)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFailsOnTooLong() {
        new Code(gameConfig, Arrays.asList(Arrays.copyOfRange(PinColor.values(), 0, gameConfig.getCodeLength() + 1)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFailsOnDuplicates() {
        List<PinColor> colors = Arrays.asList(Arrays.copyOfRange(PinColor.values(), 0, gameConfig.getCodeLength()));
        Assert.assertEquals(gameConfig.getCodeLength(), colors.size());
        colors.set(gameConfig.getCodeLength() - 1, PinColor.values()[0]); // duplicate color
        new Code(gameConfig, Arrays.asList(PinColor.RED, PinColor.BLUE, PinColor.GREEN, PinColor.PINK, PinColor.BLUE));
    }

}
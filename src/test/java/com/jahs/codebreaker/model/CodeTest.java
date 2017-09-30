package com.jahs.codebreaker.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CodeTest {

    @Test(expected = IllegalArgumentException.class)
    public void testFailsOnTooShort() {
        new Code(Arrays.asList(Arrays.copyOfRange(PinColor.values(), 0, GameConstants.CODE_LENGTH - 2)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFailsOnTooLong() {
        new Code(Arrays.asList(Arrays.copyOfRange(PinColor.values(), 0, GameConstants.CODE_LENGTH + 1)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFailsOnDuplicates() {
        List<PinColor> colors = Arrays.asList(Arrays.copyOfRange(PinColor.values(), 0, GameConstants.CODE_LENGTH));
        Assert.assertEquals(GameConstants.CODE_LENGTH, colors.size());
        colors.set(GameConstants.CODE_LENGTH - 1, PinColor.values()[0]); // duplicate color
        new Code(Arrays.asList(PinColor.RED, PinColor.BLUE, PinColor.GREEN, PinColor.PINK, PinColor.BLUE));
    }

}
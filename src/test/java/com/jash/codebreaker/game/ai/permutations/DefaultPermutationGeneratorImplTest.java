package com.jash.codebreaker.game.ai.permutations;

import com.google.common.collect.ImmutableSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultPermutationGeneratorImplTest {

    private DefaultPermutationGeneratorImpl permutationGenerator;
    private CollectingClientOutlet<String> clientOutlet;

    @Before
    public void configure() {
        permutationGenerator = new DefaultPermutationGeneratorImpl();
        clientOutlet = new CollectingClientOutlet<>();
    }


    @Test
    public void testGenerateSimplePermutationsEmpty() {
        permutationGenerator.generatePermutationsWithRepetitions(new ArrayList<>(),
                clientOutlet::acceptAll);
        Assert.assertTrue(clientOutlet.getCollected().isEmpty());
    }


    @Test
    public void testGenerateABC() {
        permutationGenerator.generatePermutationsWithRepetitions(Arrays.asList("A", "B", "C"),
                clientOutlet::acceptAll);
        Set<String> expected = ImmutableSet.<String>builder()
                .add("ABC")
                .add("ACB")
                .add("BAC")
                .add("BCA")
                .add("CAB")
                .add("CBA")
                .build();
        List<String> words  = concatWords(clientOutlet.getCollected());
        Assert.assertEquals(expected.size(), words.size());
        Assert.assertEquals(expected, ImmutableSet.copyOf(words));
    }


    @Test
    public void testGenerateAAC() {
        permutationGenerator.generatePermutationsWithRepetitions(Arrays.asList("A", "A", "C"),
                clientOutlet::acceptAll);
        Set<String> expected = ImmutableSet.<String>builder()
                .add("AAC")
                .add("ACA")
                .add("CAA")
                .build();
        List<String> words  = concatWords(clientOutlet.getCollected());
        Assert.assertEquals(expected.size(), words.size());
        Assert.assertEquals(expected, ImmutableSet.copyOf(words));
    }


    private static List<String> concatWords(List<List<String>> words) {
        List<String> reduced = words.stream()
                .map(word -> word.stream().reduce("", (a, b) -> a + b))
                .collect(Collectors.toList());
        return reduced;
    }

}
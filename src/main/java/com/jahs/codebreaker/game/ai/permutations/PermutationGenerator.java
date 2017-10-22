package com.jahs.codebreaker.game.ai.permutations;

import java.util.List;
import java.util.function.Function;

/**
 * Generates permutations.
 */
public interface PermutationGenerator {

    /**
     * Generates permutations with repetitions.
     * Eg. for source AAB it should generate:
     * AAB, ABA, BAA.
     * <T> need to have equals and hashCode implemented!
     *
     *
     * @param sourceItems
     * @param interceptingClient returns true if permutations should still be generated. False otherwise.
     * @param <T>
     */
    <T> void generatePermutationsWithRepetitions(List<T> sourceItems, Function<List<T>, Boolean> interceptingClient);

}

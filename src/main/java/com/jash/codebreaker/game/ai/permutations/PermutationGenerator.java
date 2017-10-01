package com.jash.codebreaker.game.ai.permutations;

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
     * @param sourceItems
     * @param client returns true if permutations should still be generated. False otherwise.
     * @param <T>
     */
    <T> void generatePermutationsWithRepetitions(List<T> sourceItems, Function<List<T>, Boolean> client);

}

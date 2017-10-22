package com.jahs.codebreaker.game.ai.permutations;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * Default implementation of permutations generator.
 */
public class DefaultPermutationGeneratorImpl implements PermutationGenerator {


    @Override
    public <T> void generatePermutationsWithRepetitions(List<T> sourceItems,
                                                        Function<List<T>, Boolean> interceptingClient) {
        doGeneratePermutations(sourceItems, Collections.emptyList(), interceptingClient);
    }


    private <T> boolean doGeneratePermutations(List<T> source, List<T> prefix,
                                            Function<List<T>, Boolean> interceptingClient) {
        Set<T> visitedElements = Sets.newHashSet();
        for (T element: source) {
            if (!visitedElements.contains(element)) {
                List<T> word = ImmutableList.<T>builder()
                        .addAll(prefix)
                        .add(element)
                        .build();
                List<T> remainingElements = Lists.newLinkedList();
                remainingElements.addAll(source);
                remainingElements.remove(element);

                // if there's no remaining elements we just broadcast result.
                boolean shouldContinue = true;
                if (remainingElements.isEmpty()) {
                    shouldContinue = interceptingClient.apply(word);
                } else {
                    //
                    shouldContinue = doGeneratePermutations(remainingElements, word, interceptingClient);
                }
                if (!shouldContinue) {
                    return false;
                }
                visitedElements.add(element);
            }
        }
        return true;
    }
}

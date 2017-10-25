package com.jahs.codebreaker.game;

import com.google.common.collect.Sets;
import com.jahs.codebreaker.game.ai.permutations.PermutationGenerator;
import com.jahs.codebreaker.model.Code;
import com.jahs.codebreaker.model.GameConfig;
import com.jahs.codebreaker.model.GuessResults;
import com.jahs.codebreaker.model.PinColor;
import com.jahs.codebreaker.game.ai.ResolutionContext;
import com.jahs.codebreaker.game.ai.ResponseToken;

import javax.xml.ws.Response;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Resolution context producer.
 * It generates a resolution context which is based on an existing one, but also
 * meets the criteria from the guess results.
 *
 */
public class ResolutionContextProducerImpl implements ResolutionContextProducer {

    private final GameConfig gameConfig;
    private final PermutationGenerator permutationGenerator;

    public ResolutionContextProducerImpl(GameConfig gameConfig, PermutationGenerator permutationGenerator) {
        this.gameConfig = Objects.requireNonNull(gameConfig);
        this.permutationGenerator = Objects.requireNonNull(permutationGenerator);
    }

    @Override
    public void applyGuess(ResolutionContext baseContext, GuessResults guessResults, Function<ResolutionContext, Boolean> interceptingConsumer) {
        // first, identify which fields still need to be allocated.
        List<ResponseToken> guessTokensToAllocate = ResponseToken.getTokensFromResponse(guessResults.getResponse());
        Set<Integer> sourceIndexesToAllocate = Sets.newHashSet();
        Set<Integer> guessIndexesToAllocate = gameConfig.getAllIndexes();
//        Set<PinColor> colorPool = Sets.newHashSet();
//        colorPool.addAll(PinColor.values())
        for (int i = 0; i < gameConfig.getCodeLength(); i++) {
            ResolutionContext.FieldConfig fieldConfig = baseContext.getFieldConfig(i);
            if (fieldConfig.isSelected()) {
                PinColor selectedColor = fieldConfig.getSelectedColor();
                // we won't be allocating this field.
                // let's check if this color was in the guess.
                if (guessResults.getGuess().hasColor(selectedColor)) {
                    // was the index the same as on our context?
                    int positionAtGuess = guessResults.getGuess().getColorPosition(selectedColor);
                    if (positionAtGuess == i) {
                        // same index, it means it has to be a black pin in the guess.
                        if (!removeToken(guessTokensToAllocate, ResponseToken.HIT)) {
                            return;
                        }
                    } else {
                        // it has to be a white token then.
                        if (!removeToken(guessTokensToAllocate, ResponseToken.MISPLACED)) {
                            return;
                        }
                    }
                    guessIndexesToAllocate.remove(positionAtGuess);
                }
            } else {
                sourceIndexesToAllocate.add(i);
            }
        }

        // now we need to allocate tokens to positions.
        System.out.println("Still got " + guessTokensToAllocate + " tokens to allocate.");
        List<Integer> sourceIndexesSorted = sourceIndexesToAllocate.stream().sorted().collect(Collectors.toList());
        List<Integer> guessIndexesSorted = guessIndexesToAllocate.stream().sorted().collect(Collectors.toList());
        permutationGenerator.generatePermutationsWithRepetitions(guessTokensToAllocate, perm -> acceptPermutation(baseContext,
                perm, sourceIndexesSorted, guessIndexesSorted, guessResults.getGuess(), interceptingConsumer));
    }

    private static boolean removeToken(List<ResponseToken> pool, ResponseToken token) {
        int index = pool.indexOf(token);
        if (index < 0) {
            // contradictory
            return false;
        }
        pool.remove(index);
        return true;
    }


    private boolean acceptPermutation(ResolutionContext ctx, List<ResponseToken> perm,
                                      List<Integer> sourceIndexes,
                                      List<Integer> guessIndexes,
                                      Code guess,
                                      Function<ResolutionContext, Boolean> interceptingConsumer) {
        boolean sizesMatch = (perm.size() == sourceIndexes.size() && sourceIndexes.size() == guessIndexes.size());
        if (!sizesMatch) {
            throw new IllegalArgumentException("Expected same sizes, got different.");
        }

        // now, let's apply permutation and calculate implications on the context.
        IntStream.range(0, perm.size()).forEachOrdered(i -> {
            ResponseToken token = perm.get(i);
            int sourceIndex = sourceIndexes.get(i);
            int guessIndex = guessIndexes.get(i);

        });

        return false;
    }

}

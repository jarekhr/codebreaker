package com.jahs.codebreaker.game;

import com.jahs.codebreaker.model.GuessResults;
import com.jahs.codebreaker.game.ai.ResolutionContext;

import java.util.function.Function;

/**
 * Resolution context producer takes an existing context and applies
 * guidelines from an guess results.
 *
 */
public interface ResolutionContextProducer {

    /**
     * Applies a guess.
     *
     *
     * @param initialContext
     * @param guessResults
     * @return resolution context which fulfills rules from the result OR <code>null</code> if such
     * context cannot be generated.
     */
    void applyGuess(ResolutionContext initialContext,
                                 GuessResults guessResults,
                                 Function<ResolutionContext, Boolean> interceptingConsumer);

}

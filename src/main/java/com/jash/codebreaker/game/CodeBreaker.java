package com.jash.codebreaker.game;

import com.jahs.codebreaker.model.Code;
import com.jahs.codebreaker.model.GuessHistory;

/**
 * Core player interface.
 */
public interface CodeBreaker {

    /**
     * Main method for a player: come up with a guess based on game history.
     *
     * @param guessHistory
     * @return
     */
    Code attemptGuess(GuessHistory guessHistory);

}

package com.jahs.codebreaker.game.ai;

import com.jahs.codebreaker.model.Code;
import com.jahs.codebreaker.model.GameConfig;
import com.jahs.codebreaker.model.GuessHistory;
import com.jahs.codebreaker.model.PinColor;
import com.jahs.codebreaker.game.CodeBreaker;

import java.util.Arrays;
import java.util.Objects;

public class CodeBreakerImpl implements CodeBreaker {

    private final GameConfig gameConfig;

    public CodeBreakerImpl(GameConfig gameConfig) {
        this.gameConfig = Objects.requireNonNull(gameConfig);
    }

    @Override
    public Code attemptGuess(GuessHistory guessHistory) {
        if (guessHistory.getResults().isEmpty()) {
            // this is an initial guess...
            return createInitialGuess();
        }


        return produceRegularGuess(guessHistory);
    }

    private Code produceRegularGuess(GuessHistory guessHistory) {
        ResolutionContext resolutionContext = ResolutionContext.emptyContext(gameConfig);

        // now, let's loop through guesses and produce a context.


        return null;
    }

    /**
     * Right now it does a static guess, later we could improve that to do either a random guess
     * or some guess based on statistics.
     *
     * @return
     */
    private Code createInitialGuess() {
        return new Code(gameConfig, Arrays.asList(Arrays.copyOfRange(PinColor.values(), 0, gameConfig.getCodeLength())));
    }
}

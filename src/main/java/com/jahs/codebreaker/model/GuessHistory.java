package com.jahs.codebreaker.model;

import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * Represents a history of guesses so far.
 */
public class GuessHistory {

    private final List<GuessResults> results;

    public GuessHistory(List<GuessResults> results) {
        this.results = ImmutableList.copyOf(results);
    }

    public List<GuessResults> getResults() {
        return results;
    }


}

package com.jahs.codebreaker.model;

import java.util.Objects;

/**
 * Single guess results.
 */
public class GuessResults {

    private final Code guess;
    private final Response response;

    public GuessResults(Code guess, Response response) {
        this.guess = Objects.requireNonNull(guess);
        this.response = Objects.requireNonNull(response);
    }

    public Code getGuess() {
        return guess;
    }

    public Response getResponse() {
        return response;
    }
}

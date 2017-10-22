package com.jahs.codebreaker.game.ai.permutations;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.function.Function;

public class CollectingClientOutlet <T> {

    private List<List<T>> collected = Lists.newArrayList();

    public boolean acceptAll(List<T> product) {
        collected.add(product);
        return true;
    }

    public List<List<T>> getCollected() {
        return collected;
    }
}

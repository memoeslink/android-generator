package com.memoeslink.generator.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Randomizer {
    private Long seed;
    private Random r;
    private static final HashMap<Integer, Double> ARRAY_WEIGHT_REGISTRY = new HashMap<>();

    public Randomizer() {
        bindSeed(null);
    }

    public Randomizer(Long seed) {
        bindSeed(seed);
    }

    public Long getSeed() {
        return seed;
    }

    public void bindSeed(Long seed) {
        this.seed = seed;

        if (seed != null)
            r = new Random(seed);
        else
            r = new Random();
    }

    public void unbindSeed() {
        bindSeed(null);
    }

    public int getInt(int n) {
        return r.nextInt(n);
    }

    public int getInt(int start, int n) {
        int value;
        boolean negative = false;

        if (n < 1)
            n = 1;

        if (start < 0) {
            negative = true;
            start = Math.abs(start);
        }
        value = r.nextInt(n);

        if (negative)
            value = value - start;
        else
            value = value + start;
        return value;
    }

    public boolean getBoolean() {
        return r.nextBoolean();
    }

    public float getFloat() {
        return r.nextFloat();
    }

    public double getDouble() {
        return r.nextDouble();
    }

    public long getLong() {
        return r.nextLong();
    }

    public long getLong(long n) {
        if (n < 1L)
            n = 1L;
        return (long) (r.nextDouble() * n);
    }

    public int getGaussianInt(int standardDeviation, int mean, int constraint) {
        int value;
        int tries = 999;

        try {
            do {
                value = (int) Math.round(r.nextGaussian() * standardDeviation + mean);
                tries--;
            } while (value <= constraint && tries > 0);

            if (tries == 0)
                value = constraint;
            return value;
        } catch (Exception e) {
            return constraint;
        }
    }

    public List<Integer> getIntegers(int maxNumbers, int maxValue, boolean nonRepeating) {
        List<Integer> numbers = new ArrayList<>();

        if (maxNumbers <= 0)
            maxNumbers = 1;

        if (maxValue <= 0)
            maxValue = 2;

        if (r != null) {
            for (int n = 0; n < maxNumbers; n++) {
                numbers.add(r.nextInt(maxValue + 1));
            }

            if (nonRepeating) {
                Set<Integer> set = new HashSet<>();
                set.addAll(numbers);
                numbers.clear();
                numbers.addAll(set);
            }
        }
        return numbers;
    }

    public char chooseOnWeight(WeightedChar[] weightedChars) {
        weightedChars = weightedChars != null ? weightedChars : new WeightedChar[]{};
        double completeWeight = ARRAY_WEIGHT_REGISTRY.getOrDefault(Arrays.hashCode(weightedChars), 0.0D);

        if (completeWeight == 0.0D) {
            for (WeightedChar c : weightedChars) {
                completeWeight += c.getWeight();
            }
            ARRAY_WEIGHT_REGISTRY.put(Arrays.hashCode(weightedChars), completeWeight);
        }
        double probability = getDouble() * completeWeight;
        double weight = 0.0D;

        for (WeightedChar c : weightedChars) {
            weight += c.getWeight();

            if (weight > 0.0D && weight >= probability) return c.getValue();
        }
        return new WeightedChar().getValue();
    }
}

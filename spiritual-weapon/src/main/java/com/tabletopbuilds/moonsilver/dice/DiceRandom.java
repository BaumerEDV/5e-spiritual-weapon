package com.tabletopbuilds.moonsilver.dice;

import org.assertj.core.util.Preconditions;

import java.util.Random;

public final class DiceRandom {

    private static final int SEED = 42;

    private static Random random = new Random(SEED);


    private DiceRandom() {
        // static util class
    }

    public static DieRollResult d(int size) {
        Preconditions.checkArgument(size > 0, "Die size must be positive integer");

        return new DieRollResult(random.nextInt(size) + 1, size);
    }


}

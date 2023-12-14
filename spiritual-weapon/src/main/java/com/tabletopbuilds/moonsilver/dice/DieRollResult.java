package com.tabletopbuilds.moonsilver.dice;

import org.assertj.core.util.Preconditions;

public class DieRollResult {

    public final int value;
    public final int dieSize;

    public DieRollResult(int value, int dieSize) {
        Preconditions.checkArgument(value > 0, "die rolls can't be negative");
        Preconditions.checkArgument(value <= dieSize, "die rolls can't be bigger than the die");

        this.value = value;
        this.dieSize = dieSize;
    }
}

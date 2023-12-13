package com.tabletopbuilds.moonsilver.dice;

public class AttackRoll {

    public final int total;
    public final boolean isCrit;

    public AttackRoll(int modifier) {
        var d20Roll = DiceRandom.d(20);
        this.total = d20Roll.value + modifier;
        this.isCrit = d20Roll.value == 20;
    }
}

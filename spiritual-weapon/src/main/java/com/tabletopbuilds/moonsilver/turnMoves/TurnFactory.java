package com.tabletopbuilds.moonsilver.turnMoves;

import com.tabletopbuilds.moonsilver.dice.DiceRandom;
import com.tabletopbuilds.moonsilver.sidesOfCombat.Side;

import java.util.List;

public final class TurnFactory {

    private TurnFactory() {
    }

    public static TurnMove clericMace_Lvl3(Side target) {
        return () -> {
            System.out.println("cleric mace attack");
            var attack = Attack.from(+2, List.of(() -> DiceRandom.d(6)), 2);
            target.processAttack(attack);
        };
    }

    public static TurnMove clericSpiritualWeaponAndMace_Lvl3(Side target) {
        return () -> {
            clericMace_Lvl3(target).apply();
            System.out.println("cleric spiritual weapon attack");
            var attack = Attack.from(+5, List.of(() -> DiceRandom.d(8)), 3);
            target.processAttack(attack);
        };
    }

    public static TurnMove bugbearMorningstar(Side target) {
        return () -> {
            System.out.println("bugbear morningstar attack");
            var attack = Attack.from(+4, List.of(() -> DiceRandom.d(8), () -> DiceRandom.d(8)), 2);
            target.processAttack(attack);
        };
    }

    public static TurnMove baseline_lvl3(Side target) {
        return () -> {
            System.out.println("baseline attack");
            var attack = Attack.from(+5, List.of(() -> DiceRandom.d(10), () -> DiceRandom.d(6)), 3);
            target.processAttack(attack);
        };
    }

    public static TurnMove bulette_bite(Side target) {
        return () -> {
            System.out.println("bulette bite");
            var attack = Attack.from(+7, List.of(() -> DiceRandom.d(12), () -> DiceRandom.d(12), () -> DiceRandom.d(12), () -> DiceRandom.d(12)), 4);
            target.processAttack(attack);
        };
    }

    public static TurnMove goblin_Scimitar(Side target) {
        return () -> {
            System.out.println("goblin scimitar");
            var attack = Attack.from(+4, List.of(() -> DiceRandom.d(6)), 2);
            target.processAttack(attack);
        };
    }
}

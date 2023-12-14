package com.tabletopbuilds.moonsilver.turnMoves;

import com.tabletopbuilds.moonsilver.dice.AttackRoll;
import com.tabletopbuilds.moonsilver.dice.DieRollResult;
import org.assertj.core.util.Preconditions;

import java.util.List;
import java.util.function.Supplier;

public class Attack {
    public final int toHit;
    public final boolean isCrit;
    public final int damage;

    private Attack(int toHit, boolean isCrit, int damage) {
        Preconditions.checkArgument(damage >= 0, "damage must be at least 0");

        this.toHit = toHit;
        this.isCrit = isCrit;
        this.damage = damage;
    }

    static Attack from(int modifier, List<Supplier<DieRollResult>> damageRollDice, int damageRollModifier) {
        Preconditions.checkNotNull(damageRollDice);

        var roll = new AttackRoll(modifier);
        int damage = damageRollModifier;
        for (var damageDie : damageRollDice) {
            damage += damageDie.get().value;
        }
        if (roll.isCrit) {
            for (var damageDie : damageRollDice) {
                damage += damageDie.get().value;
            }
        }
        return new Attack(roll.total, roll.isCrit, damage);
    }
}

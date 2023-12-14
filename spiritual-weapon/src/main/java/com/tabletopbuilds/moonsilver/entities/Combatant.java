package com.tabletopbuilds.moonsilver.entities;

import com.tabletopbuilds.moonsilver.dice.DiceRandom;
import com.tabletopbuilds.moonsilver.turnMoves.TurnMove;
import org.assertj.core.util.Preconditions;

import java.util.List;

public class Combatant {

    private final int initiativeModifier;
    private final List<TurnMove> strategiesPerTurn; // last one repeats per contract

    public final int ac;
    private int hp;

    public final String name;
    public final int initiativeRoll;

    private boolean isDead = false;


    public Combatant(int initiativeModifier, List<TurnMove> strategiesPerTurn, String name, int ac, int hp) {
        Preconditions.checkArgument(name != null, "name can't be null");
        Preconditions.checkArgument(strategiesPerTurn != null, "strategies can't be null");
        Preconditions.checkArgument(strategiesPerTurn.size() != 0, "must have at least 1 strategy");
        Preconditions.checkArgument(ac > 0, "negative AC implausible");
        Preconditions.checkArgument(hp > 0, "negative HP not allowed");

        this.initiativeModifier = initiativeModifier;
        this.strategiesPerTurn = strategiesPerTurn;
        this.name = name;
        this.initiativeRoll = DiceRandom.d(20).value + initiativeModifier;
        this.ac = ac;
        this.hp = hp;
    }

    public boolean isDead() {
        return isDead;
    }

    public void runStrategyForRound(int round) {
        Preconditions.checkArgument(round > 0, "round must be positive");
        Preconditions.checkArgument(!isDead, "Dead people can't attack!");

        if (round > strategiesPerTurn.size()) {
            round = strategiesPerTurn.size();
        }

        System.out.println(name + "performs their move for round " + round);
        strategiesPerTurn.get(round - 1).apply();
    }

    public void damage(int damage) {
        Preconditions.checkArgument(damage >= 0, "negative damage not allowed");
        Preconditions.checkArgument(!isDead, "dead people can't take damage");
        hp -= damage;

        if (hp <= 0) {
            isDead = true;
        }
    }
}

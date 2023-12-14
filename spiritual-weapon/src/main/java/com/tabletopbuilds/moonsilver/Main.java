package com.tabletopbuilds.moonsilver;

import com.tabletopbuilds.moonsilver.entities.Combatant;
import com.tabletopbuilds.moonsilver.entities.CombatantFactory;
import com.tabletopbuilds.moonsilver.sidesOfCombat.PlayerSide;
import com.tabletopbuilds.moonsilver.sidesOfCombat.Side;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Main {

    public static final int ITERATIONS = 100;

    public static void main(String[] args) throws InterruptedException {

        ArrayList<Integer> damageTaken = new ArrayList<>(ITERATIONS);
        ArrayList<Integer> roundsTillCombatEnd = new ArrayList<>(ITERATIONS);

        for (int iteration = 1; iteration < ITERATIONS; iteration++) {
            PlayerSide clericParty = new PlayerSide("Cleric's Party", 17);
            Side bugbears = new Side("The Bugbears", 16);

            var combatantFactory = new CombatantFactory(clericParty, bugbears);

            ArrayList<Combatant> initiativeTracker = new ArrayList<>();


            // add combatants to the combat

            var cleric = combatantFactory.cleric_Lvl3_withSpiritualWeapon();
            clericParty.addCombatant(cleric);
            initiativeTracker.add(cleric);

            Stream.of("baseline 1", "baseline 2", "baseline 3")
                    .forEach(name -> {
                        var baseline = combatantFactory.baseline_Lvl3(name);
                        clericParty.addCombatant(baseline);
                        initiativeTracker.add(baseline);
                    });


            Stream.of("bugbear 1", "bugbear 2", "bugbear 3")
                    .forEach(name -> {
                        var bugbear1 = combatantFactory.bugbear_Cr1(name);
                        bugbears.addCombatant(bugbear1);
                        initiativeTracker.add(bugbear1);
                    });


            // sort initiative

            initiativeTracker.sort((c1, c2) -> Integer.compare(c2.initiativeRoll, c1.initiativeRoll));

            System.out.println("Initiative order:");
            for (var c : initiativeTracker) {
                System.out.println(c.name + ": " + c.initiativeRoll);
            }

            for (int round = 1; ; round++) {

                for (var c : initiativeTracker) {
                    System.out.println(c.name + " takes their turn");
                    if (c.isDead()) {
                        continue;
                    }
                    c.runStrategyForRound(round);
                }

                if (!bugbears.isAnyoneStillStanding()) {
                    System.out.println("The bugbears have been defeated");
                    System.out.println("The players took " + clericParty.damageTaken() + " damage");
                    damageTaken.add(clericParty.damageTaken());
                    roundsTillCombatEnd.add(round);
                    break;
                }

            }

            System.out.println("run end of iteration " + iteration);
        }

        System.out.println("average damage taken: " + (float) damageTaken.stream().reduce(Integer::sum).get() / ITERATIONS);
        System.out.println("average rounds till combat end: " + (float) roundsTillCombatEnd.stream().reduce(Integer::sum).get() / ITERATIONS);

    }

}
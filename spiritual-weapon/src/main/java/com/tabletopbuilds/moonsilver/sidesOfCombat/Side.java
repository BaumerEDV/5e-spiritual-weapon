package com.tabletopbuilds.moonsilver.sidesOfCombat;

import com.tabletopbuilds.moonsilver.entities.Combatant;
import com.tabletopbuilds.moonsilver.turnMoves.Attack;
import org.assertj.core.util.Preconditions;

import java.util.ArrayList;

public class Side {

    public final String name;
    public final int ac;

    private final ArrayList<Combatant> combatants = new ArrayList<>();


    public Side(String name, int ac) {
        Preconditions.checkArgument(name != null, "side name must be provided");

        this.name = name;
        this.ac = ac;
    }

    public void addCombatant(Combatant combatant) {
        Preconditions.checkNotNull(combatant);
        Preconditions.checkArgument(!combatants.contains(combatant), "combatants must be unique");

        combatants.add(combatant);
    }

    public void processAttack(Attack attack) {
        var combatantToBeAttackedOptional = combatants.stream().filter(combatant -> !combatant.isDead()).findFirst();

        if (combatantToBeAttackedOptional.isEmpty()) {
            System.out.print("Side " + name + "is defeated");
            return;
        }

        var combatantToBeAttacked = combatantToBeAttackedOptional.get();

        if (combatantToBeAttacked.ac <= attack.toHit || attack.isCrit) {
            System.out.print("Attack hit " + combatantToBeAttacked.name + " for " + attack.damage + ". critical = " + attack.isCrit);
            combatantToBeAttacked.damage(attack.damage);
        }
    }

    public boolean isAnyoneStillStanding() {
        return combatants.stream().anyMatch(combatant -> !combatant.isDead());
    }

}

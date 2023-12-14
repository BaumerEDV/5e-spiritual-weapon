package com.tabletopbuilds.moonsilver.sidesOfCombat;

import com.tabletopbuilds.moonsilver.turnMoves.Attack;

public class PlayerSide extends Side {

    private int damageTaken = 0;

    public PlayerSide(String name, int ac) {
        super(name, ac);
    }

    @Override
    public void processAttack(Attack attack) {


        if (ac <= attack.toHit || attack.isCrit) {
            System.out.print("Attack hit player side for " + attack.damage + ". critical = " + attack.isCrit);
            damageTaken += attack.damage;
        }
    }

    public int damageTaken() {
        return damageTaken;
    }
}

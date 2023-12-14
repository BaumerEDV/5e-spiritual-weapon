package com.tabletopbuilds.moonsilver.entities;

import com.tabletopbuilds.moonsilver.sidesOfCombat.Side;
import com.tabletopbuilds.moonsilver.turnMoves.TurnFactory;
import com.tabletopbuilds.moonsilver.turnMoves.TurnMove;
import org.assertj.core.util.Preconditions;

import java.util.List;
import java.util.stream.Stream;

public final class CombatantFactory {

    private final Side playerParty;
    private final Side npcParty;

    public CombatantFactory(Side playerParty, Side npcParty) {
        Preconditions.checkNotNull(playerParty);
        Preconditions.checkNotNull(npcParty);

        this.playerParty = playerParty;
        this.npcParty = npcParty;
    }

    public Combatant cleric_Lvl3() {
        return new Combatant(-1, List.of(TurnFactory.clericMace_Lvl3(npcParty)), "Cleric Lvl 3", 19, 27);
    }

    public Combatant cleric_Lvl3_withSpiritualWeapon() {
        List<TurnMove> strategy = Stream.of(TurnFactory.clericSpiritualWeaponAndMace_Lvl3(npcParty), // 1
                TurnFactory.clericSpiritualWeaponAndMace_Lvl3(npcParty), // 2
                TurnFactory.clericSpiritualWeaponAndMace_Lvl3(npcParty), // 3
                TurnFactory.clericSpiritualWeaponAndMace_Lvl3(npcParty), // 4
                TurnFactory.clericSpiritualWeaponAndMace_Lvl3(npcParty), // 5
                TurnFactory.clericSpiritualWeaponAndMace_Lvl3(npcParty), // 6
                TurnFactory.clericSpiritualWeaponAndMace_Lvl3(npcParty), // 7
                TurnFactory.clericSpiritualWeaponAndMace_Lvl3(npcParty), // 8
                TurnFactory.clericSpiritualWeaponAndMace_Lvl3(npcParty), // 9
                TurnFactory.clericSpiritualWeaponAndMace_Lvl3(npcParty), // 10
                TurnFactory.clericMace_Lvl3(npcParty) // repeats
        ).toList();
        return new Combatant(-1, strategy, "Cleric Lvl 3", 19, 27);
    }

    public Combatant baseline_Lvl3(String name) {
        return new Combatant(+2, List.of(TurnFactory.baseline_lvl3(npcParty)), name, 17, 24);
    }

    public Combatant bugbear_Cr1(String name) {
        return new Combatant(+2, List.of(TurnFactory.bugbearMorningstar(playerParty)), name, 16, 27);
    }

    public Combatant bulette_Cr5(String name) {
        return new Combatant(+0, List.of(TurnFactory.bulette_bite(playerParty)), name, 17, 94);
    }

    public Combatant goblin_Cr1_4(String name) {
        return new Combatant(+2, List.of(TurnFactory.goblin_Scimitar(playerParty)), name, 15, 7);
    }
}

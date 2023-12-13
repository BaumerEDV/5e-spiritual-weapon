package com.tabletopbuilds.moonsilver.dice;

import org.assertj.core.api.WithAssertions;
import org.junit.Test;

public class DiceRandomTest implements WithAssertions {

    @Test
    public void no_negatives() {
        assertThatThrownBy(() -> DiceRandom.d(-1));
        assertThatThrownBy(() -> DiceRandom.d(-100));
    }

    @Test
    public void no_zero() {
        assertThatThrownBy(() -> DiceRandom.d(0));
    }

    @Test
    public void d20_isBalanced_andOnlyYieldsResultsInRange() {
        int[] results = new int[20];

        final int iterations = 10000;
        for (int i = 0; i < iterations; i++) {
            results[DiceRandom.d(20).value - 1] += 1;
        }

        for (int result : results) {
            System.out.println(result);
            assertThat(result).isBetween(iterations / 23, iterations / 17);
        }
    }

    @Test
    public void correctly_sets_die_size() {
        assertThat(DiceRandom.d(20).dieSize).isEqualTo(20);
        assertThat(DiceRandom.d(4).dieSize).isEqualTo(4);
    }
}

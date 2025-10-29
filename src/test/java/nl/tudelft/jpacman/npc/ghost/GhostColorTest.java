package nl.tudelft.jpacman.npc.ghost;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for the GhostColor enum.
 */
class GhostColorTest {

    /**
     * Test that all expected ghost colors exist.
     */
    @Test
    void testGhostColors() {
        GhostColor[] colors = GhostColor.values();
        assertThat(colors).hasSize(4);
        assertThat(colors).contains(
            GhostColor.RED,
            GhostColor.CYAN,
            GhostColor.PINK,
            GhostColor.ORANGE
        );
    }

    /**
     * Test valueOf method for RED.
     */
    @Test
    void testValueOfRed() {
        assertThat(GhostColor.valueOf("RED")).isEqualTo(GhostColor.RED);
    }

    /**
     * Test valueOf method for CYAN.
     */
    @Test
    void testValueOfCyan() {
        assertThat(GhostColor.valueOf("CYAN")).isEqualTo(GhostColor.CYAN);
    }

    /**
     * Test valueOf method for PINK.
     */
    @Test
    void testValueOfPink() {
        assertThat(GhostColor.valueOf("PINK")).isEqualTo(GhostColor.PINK);
    }

    /**
     * Test valueOf method for ORANGE.
     */
    @Test
    void testValueOfOrange() {
        assertThat(GhostColor.valueOf("ORANGE")).isEqualTo(GhostColor.ORANGE);
    }
}

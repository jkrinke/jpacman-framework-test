package nl.tudelft.jpacman.board;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for the Direction enum.
 */
class DirectionTest {

    /**
     * Expected number of directions in the Direction enum.
     */
    private static final int EXPECTED_DIRECTION_COUNT = 4;

    /**
     * Test that NORTH has correct delta values.
     */
    @Test
    void testNorthDeltas() {
        assertThat(Direction.NORTH.getDeltaX()).isEqualTo(0);
        assertThat(Direction.NORTH.getDeltaY()).isEqualTo(-1);
    }

    /**
     * Test that SOUTH has correct delta values.
     */
    @Test
    void testSouthDeltas() {
        assertThat(Direction.SOUTH.getDeltaX()).isEqualTo(0);
        assertThat(Direction.SOUTH.getDeltaY()).isEqualTo(1);
    }

    /**
     * Test that EAST has correct delta values.
     */
    @Test
    void testEastDeltas() {
        assertThat(Direction.EAST.getDeltaX()).isEqualTo(1);
        assertThat(Direction.EAST.getDeltaY()).isEqualTo(0);
    }

    /**
     * Test that WEST has correct delta values.
     */
    @Test
    void testWestDeltas() {
        assertThat(Direction.WEST.getDeltaX()).isEqualTo(-1);
        assertThat(Direction.WEST.getDeltaY()).isEqualTo(0);
    }

    /**
     * Test that all expected directions exist.
     */
    @Test
    void testAllDirections() {
        Direction[] directions = Direction.values();
        assertThat(directions).hasSize(EXPECTED_DIRECTION_COUNT);
        assertThat(directions).contains(
            Direction.NORTH,
            Direction.SOUTH,
            Direction.EAST,
            Direction.WEST
        );
    }
}

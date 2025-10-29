package nl.tudelft.jpacman.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for the Unit class.
 */
class UnitTest {

    private Unit unit;

    /**
     * Set up a basic unit for testing.
     */
    @BeforeEach
    void setUp() {
        unit = new BasicUnit();
    }

    /**
     * Test that a new unit faces EAST by default.
     */
    @Test
    void testDefaultDirection() {
        assertThat(unit.getDirection()).isEqualTo(Direction.EAST);
    }

    /**
     * Test that setDirection changes the direction.
     */
    @Test
    void testSetDirection() {
        unit.setDirection(Direction.NORTH);
        assertThat(unit.getDirection()).isEqualTo(Direction.NORTH);
        
        unit.setDirection(Direction.SOUTH);
        assertThat(unit.getDirection()).isEqualTo(Direction.SOUTH);
    }

    /**
     * Test that a new unit has no square.
     */
    @Test
    void testHasSquareInitially() {
        assertThat(unit.hasSquare()).isFalse();
    }

    /**
     * Test that squaresAheadOf returns the correct square.
     */
    @Test
    void testSquaresAheadOf() {
        Square currentSquare = mock(Square.class);
        Square nextSquare = mock(Square.class);
        Square finalSquare = mock(Square.class);
        
        when(currentSquare.getSquareAt(Direction.EAST)).thenReturn(nextSquare);
        when(nextSquare.getSquareAt(Direction.EAST)).thenReturn(finalSquare);
        
        // Mock the getOccupants for the invariant check
        when(currentSquare.getOccupants()).thenReturn(java.util.Collections.singletonList(unit));
        
        unit.occupy(currentSquare);
        unit.setDirection(Direction.EAST);
        
        assertThat(unit.squaresAheadOf(1)).isEqualTo(nextSquare);
        assertThat(unit.squaresAheadOf(2)).isEqualTo(finalSquare);
    }

    /**
     * Test that leaveSquare removes the unit from the square.
     */
    @Test
    void testLeaveSquare() {
        Square square = mock(Square.class);
        
        // Mock the getOccupants for the invariant check
        when(square.getOccupants()).thenReturn(java.util.Collections.singletonList(unit));
        
        unit.occupy(square);
        assertThat(unit.hasSquare()).isTrue();
        
        unit.leaveSquare();
        assertThat(unit.hasSquare()).isFalse();
    }
}

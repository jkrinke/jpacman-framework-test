package nl.tudelft.jpacman.board;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Test various aspects of board.
 *
 * @author Jeroen Roosen 
 */
class BoardTest {

    private static final int MAX_WIDTH = 2;
    private static final int MAX_HEIGHT = 3;

    private final Square[][] grid = {
        { mock(Square.class), mock(Square.class), mock(Square.class) },
        { mock(Square.class), mock(Square.class), mock(Square.class) },
    };
    private final Board board = new Board(grid);

    /**
     * Verifies the board has the correct width.
     */
    @Test
    void verifyWidth() {
        assertThat(board.getWidth()).isEqualTo(MAX_WIDTH);
    }

    /**
     * Verifies the board has the correct height.
     */
    @Test
    void verifyHeight() {
        assertThat(board.getHeight()).isEqualTo(MAX_HEIGHT);
    }

    /**
     * Verify that squares at key positions are properly set.
     * @param x Horizontal coordinate of relevant cell.
     * @param y Vertical coordinate of relevant cell.
     */
    @ParameterizedTest
    @CsvSource({
        "0, 0",
        "1, 2",
        "0, 1"
    })
    void testSquareAt(int x, int y) {
        assertThat(board.squareAt(x, y)).isEqualTo(grid[x][y]);
    }

    /**
     * Test that withinBorders returns true for valid positions.
     */
    @Test
    void testWithinBordersValid() {
        assertThat(board.withinBorders(0, 0)).isTrue();
        assertThat(board.withinBorders(1, 2)).isTrue();
        assertThat(board.withinBorders(0, 1)).isTrue();
        assertThat(board.withinBorders(1, 1)).isTrue();
    }

    /**
     * Test that withinBorders returns false for invalid positions.
     */
    @Test
    void testWithinBordersInvalid() {
        assertThat(board.withinBorders(-1, 0)).isFalse();
        assertThat(board.withinBorders(0, -1)).isFalse();
        assertThat(board.withinBorders(2, 0)).isFalse();
        assertThat(board.withinBorders(0, 3)).isFalse();
        assertThat(board.withinBorders(2, 3)).isFalse();
    }
}

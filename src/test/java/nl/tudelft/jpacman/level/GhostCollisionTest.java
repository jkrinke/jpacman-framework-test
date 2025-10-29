package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.sprite.PacManSprites;
import nl.tudelft.jpacman.sprite.Sprite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class to verify Ghost-Ghost collision behavior.
 * This test demonstrates that ghosts can move into the same square.
 */
class GhostCollisionTest {

    /**
     * Point value for test ghosts.
     */
    private static final int TEST_GHOST_POINTS = 100;

    private Level level;
    private Ghost ghost1;
    private Ghost ghost2;
    private Square square2;

    /**
     * Set up the test environment with two ghosts on adjacent squares.
     */
    @BeforeEach
    void setUp() {
        Board board = createTestBoard();
        Square square1 = board.squareAt(0, 0);
        square2 = board.squareAt(1, 0);
        
        ghost1 = new TestGhost();
        ghost2 = new TestGhost();

        List<Ghost> ghosts = createGhostList();
        List<Square> startSquares = createStartSquaresList(square1);
        CollisionMap collisionMap = new DefaultPlayerInteractionMap();
        
        level = new Level(board, ghosts, startSquares, collisionMap);

        ghost1.occupy(square1);
        ghost2.occupy(square2);
    }

    /**
     * Creates a simple 2x1 test board.
     * 
     * @return Board configured for testing.
     */
    private Board createTestBoard() {
        PacManSprites sprites = mock(PacManSprites.class);
        Sprite mockSprite = mock(Sprite.class);
        when(sprites.getGroundSprite()).thenReturn(mockSprite);
        
        BoardFactory boardFactory = new BoardFactory(sprites);
        Square[][] grid = new Square[2][1];
        grid[0][0] = boardFactory.createGround();
        grid[1][0] = boardFactory.createGround();
        
        return boardFactory.createBoard(grid);
    }

    /**
     * Creates a list containing the test ghosts.
     * 
     * @return List of ghosts for testing.
     */
    private List<Ghost> createGhostList() {
        List<Ghost> ghosts = new ArrayList<>();
        ghosts.add(ghost1);
        ghosts.add(ghost2);
        return ghosts;
    }

    /**
     * Creates a list of start squares for the level.
     * 
     * @param square1 The starting square.
     * @return List of start squares.
     */
    private List<Square> createStartSquaresList(Square square1) {
        List<Square> startSquares = new ArrayList<>();
        startSquares.add(square1);
        return startSquares;
    }

    /**
     * Test that verifies two ghosts can move to the same square.
     * This test verifies the Ghost-Ghost collision handler allows
     * ghosts to pass through each other without any interaction.
     */
    @Test
    void testGhostsCanShareSquare() {
        // Start the level
        level.start();
        
        // Move ghost1 east to square2 where ghost2 already is
        level.move(ghost1, Direction.EAST);
        
        // Stop the level immediately to prevent NPCs from moving
        level.stop();
        
        // Both ghosts should now be on square2
        // The Ghost-Ghost collision handler allows them to pass through each other
        assertThat(ghost1.getSquare()).isEqualTo(square2);
        assertThat(ghost2.getSquare()).isEqualTo(square2);
        
        // Verify both ghosts are in the occupants list
        assertThat(square2.getOccupants()).contains(ghost1, ghost2);
    }

    /**
     * Simple test ghost implementation for testing purposes.
     */
    private static class TestGhost extends Ghost {
        TestGhost() {
            super(createSpriteMap(), TEST_GHOST_POINTS, 1);
        }

        @Override
        public Optional<Direction> nextAiMove() {
            return Optional.empty();
        }
        
        private static Map<Direction, Sprite> createSpriteMap() {
            Map<Direction, Sprite> spriteMap = new EnumMap<>(Direction.class);
            Sprite mockSprite = mock(Sprite.class);
            for (Direction dir : Direction.values()) {
                spriteMap.put(dir, mockSprite);
            }
            return spriteMap;
        }
    }
}

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
import java.util.HashMap;
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

    private Level level;
    private Ghost ghost1;
    private Ghost ghost2;
    private Square square1;
    private Square square2;

    /**
     * Set up the test environment with two ghosts on adjacent squares.
     */
    @BeforeEach
    void setUp() {
        PacManSprites sprites = mock(PacManSprites.class);
        Sprite mockSprite = mock(Sprite.class);
        when(sprites.getGroundSprite()).thenReturn(mockSprite);
        
        BoardFactory boardFactory = new BoardFactory(sprites);
        
        // Create a simple 2x1 board
        Square[][] grid = new Square[2][1];
        grid[0][0] = boardFactory.createGround();
        grid[1][0] = boardFactory.createGround();
        
        Board board = boardFactory.createBoard(grid);
        square1 = board.squareAt(0, 0);
        square2 = board.squareAt(1, 0);
        
        ghost1 = new TestGhost();
        ghost2 = new TestGhost();

        List<Ghost> ghosts = new ArrayList<>();
        ghosts.add(ghost1);
        ghosts.add(ghost2);

        List<Square> startSquares = new ArrayList<>();
        startSquares.add(square1);

        CollisionMap collisionMap = new DefaultPlayerInteractionMap();
        level = new Level(board, ghosts, startSquares, collisionMap);

        ghost1.occupy(square1);
        ghost2.occupy(square2);
    }

    /**
     * Test that verifies two ghosts can move to the same square.
     * This demonstrates the gameplay flaw where Ghost-Ghost collisions
     * are not explicitly handled.
     */
    @Test
    void testGhostsCanShareSquare() {
        // Start the level
        level.start();
        
        // Move ghost1 east to square2 where ghost2 already is
        level.move(ghost1, Direction.EAST);
        
        // Both ghosts should now be on square2
        // This test passes, showing that ghosts can occupy the same square
        // The collision handler is called but does nothing since no 
        // Ghost-Ghost handler is defined
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
            super(createSpriteMap(), 100, 1);
        }

        @Override
        public Optional<Direction> nextAiMove() {
            return Optional.empty();
        }
        
        private static Map<Direction, Sprite> createSpriteMap() {
            Map<Direction, Sprite> spriteMap = new HashMap<>();
            Sprite mockSprite = mock(Sprite.class);
            for (Direction dir : Direction.values()) {
                spriteMap.put(dir, mockSprite);
            }
            return spriteMap;
        }
    }
}

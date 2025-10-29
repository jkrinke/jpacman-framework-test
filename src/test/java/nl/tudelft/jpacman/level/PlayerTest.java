package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.sprite.AnimatedSprite;
import nl.tudelft.jpacman.sprite.Sprite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Tests for the Player class.
 */
class PlayerTest {

    /**
     * Test point value for adding to player score.
     */
    private static final int TEST_POINTS_FIRST = 10;
    
    /**
     * Test point value for second addition to player score.
     */
    private static final int TEST_POINTS_SECOND = 20;
    
    /**
     * Expected total score after both point additions.
     */
    private static final int EXPECTED_TOTAL_SCORE = 30;

    private Player player;
    private Map<Direction, Sprite> sprites;
    private AnimatedSprite deathSprite;

    /**
     * Set up a player for testing.
     */
    @BeforeEach
    void setUp() {
        sprites = new HashMap<>();
        sprites.put(Direction.NORTH, mock(Sprite.class));
        sprites.put(Direction.SOUTH, mock(Sprite.class));
        sprites.put(Direction.EAST, mock(Sprite.class));
        sprites.put(Direction.WEST, mock(Sprite.class));
        deathSprite = mock(AnimatedSprite.class);
        player = new Player(sprites, deathSprite);
    }

    /**
     * Test that a new player is alive.
     */
    @Test
    void testIsAliveInitially() {
        assertThat(player.isAlive()).isTrue();
    }

    /**
     * Test that a new player has zero score.
     */
    @Test
    void testInitialScore() {
        assertThat(player.getScore()).isEqualTo(0);
    }

    /**
     * Test that addPoints increases the score.
     */
    @Test
    void testAddPoints() {
        player.addPoints(TEST_POINTS_FIRST);
        assertThat(player.getScore()).isEqualTo(TEST_POINTS_FIRST);
        player.addPoints(TEST_POINTS_SECOND);
        assertThat(player.getScore()).isEqualTo(EXPECTED_TOTAL_SCORE);
    }

    /**
     * Test that setAlive(false) kills the player.
     */
    @Test
    void testSetAliveFalse() {
        player.setAlive(false);
        assertThat(player.isAlive()).isFalse();
        verify(deathSprite).restart();
    }

    /**
     * Test that setAlive(true) revives the player.
     */
    @Test
    void testSetAliveTrue() {
        player.setAlive(false);
        player.setAlive(true);
        assertThat(player.isAlive()).isTrue();
    }

    /**
     * Test that getSprite returns the correct sprite when alive.
     */
    @Test
    void testGetSpriteWhenAlive() {
        player.setDirection(Direction.NORTH);
        assertThat(player.getSprite()).isEqualTo(sprites.get(Direction.NORTH));
    }

    /**
     * Test that getSprite returns the death sprite when dead.
     */
    @Test
    void testGetSpriteWhenDead() {
        player.setAlive(false);
        assertThat(player.getSprite()).isEqualTo(deathSprite);
    }
}

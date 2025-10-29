package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.sprite.Sprite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Tests for the Pellet class.
 */
class PelletTest {

    /**
     * Test point value for the pellet.
     */
    private static final int TEST_PELLET_VALUE = 10;

    private Pellet pellet;
    private Sprite sprite;

    /**
     * Set up a pellet with mocked sprite for testing.
     */
    @BeforeEach
    void setUp() {
        sprite = mock(Sprite.class);
        pellet = new Pellet(TEST_PELLET_VALUE, sprite);
    }

    /**
     * Test that getValue returns the correct point value.
     */
    @Test
    void testGetValue() {
        assertThat(pellet.getValue()).isEqualTo(TEST_PELLET_VALUE);
    }

    /**
     * Test that getSprite returns the correct sprite.
     */
    @Test
    void testGetSprite() {
        assertThat(pellet.getSprite()).isEqualTo(sprite);
    }
}

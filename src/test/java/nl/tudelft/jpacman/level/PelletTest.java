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

    private Pellet pellet;
    private Sprite sprite;

    /**
     * Set up a pellet with mocked sprite for testing.
     */
    @BeforeEach
    void setUp() {
        sprite = mock(Sprite.class);
        final int points = 10;
        pellet = new Pellet(points, sprite);
    }

    /**
     * Test that getValue returns the correct point value.
     */
    @Test
    void testGetValue() {
        assertThat(pellet.getValue()).isEqualTo(10);
    }

    /**
     * Test that getSprite returns the correct sprite.
     */
    @Test
    void testGetSprite() {
        assertThat(pellet.getSprite()).isEqualTo(sprite);
    }
}

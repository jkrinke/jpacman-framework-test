package nl.tudelft.jpacman;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for the PacmanConfigurationException class.
 */
class PacmanConfigurationExceptionTest {

    /**
     * Test creating exception with message only.
     */
    @Test
    void testExceptionWithMessage() {
        String message = "Configuration error";
        PacmanConfigurationException exception = new PacmanConfigurationException(message);
        
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isNull();
    }

    /**
     * Test creating exception with message and cause.
     */
    @Test
    void testExceptionWithMessageAndCause() {
        String message = "Configuration error";
        Throwable cause = new RuntimeException("Root cause");
        PacmanConfigurationException exception = new PacmanConfigurationException(message, cause);
        
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isEqualTo(cause);
    }

    /**
     * Test that exception can be thrown.
     */
    @Test
    void testExceptionCanBeThrown() {
        assertThatThrownBy(() -> {
            throw new PacmanConfigurationException("Test error");
        })
        .isInstanceOf(PacmanConfigurationException.class)
        .hasMessage("Test error");
    }
}

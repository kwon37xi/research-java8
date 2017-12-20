package javacoding.guidelines.ch06;

import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.ValidationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

public class ValidateOutputTest {
    private ValidateOutput validateOutput = null;

    @Before
    public void setUp() throws Exception {
        validateOutput = new ValidateOutput();
    }

    @Test
    public void normalize() {
        assertThat(validateOutput.normalize("안녕? ㅇㅏㄴ hello 123! 中國語 Über")).isEqualTo("안녕? 아ᄂ hello 123! 中國語 Über");
    }

    @Test
    public void validate() throws ValidationException {
        assertThat(validateOutput.validate("greetings", "Hello World 123")).isEqualTo("Hello World 123");
    }


    @Test
    public void validate_fail() throws ValidationException {
        try {
            assertThat(validateOutput.validate("greetings", "Hello <World> 123")).isEqualTo("hello world 123");
            failBecauseExceptionWasNotThrown(ValidationException.class);
        } catch (ValidationException ex) {
            assertThat(ex).hasMessage("Improper format in greetings field");
        }
    }

    @Test
    public void HTMLEntityEncode() {
        assertThat("[Hello] <World> 안녕 세상아~ Über \t \uD83D\uDE00 \uD83D\uDE03 \uD83D\uDE04 \uD83D\uDE01 \uD83D\uDE06 \uD83D\uDE05 \uD83D\uDE02?").isEqualTo("[Hello] <World> 안녕 세상아~ Über \t \uD83D\uDE00 \uD83D\uDE03 \uD83D\uDE04 \uD83D\uDE01 \uD83D\uDE06 \uD83D\uDE05 \uD83D\uDE02?");
    }
}

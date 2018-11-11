package daggerok;

import daggerok.extensions.CaptureSystemOutput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@CaptureSystemOutput
@DisplayName("JUnit 5 modern tests")
class AppJUnit5Test {

  @Test
  void test(final CaptureSystemOutput.OutputCapture outputCapture) {

    outputCapture.expect((containsString("'assert before'")));
    System.out.println("see line 20: we are expecting this string: 'assert before'");

    System.out.println("see next line 20: 'assert after'");
    assertThat(outputCapture.toString(), containsString("'assert after'"));
  }

  @Test
  @DisplayName("Positive test")
  void testPositive() {
    assertThat(true, is(true));
  }

  @Test
  @DisplayName("Negative test")
  void testNegative() {

    assertThrows(AssertionError.class,
                 () -> assertThat(false, is(true)),
                 "Assertion error should thrown");
  }
}

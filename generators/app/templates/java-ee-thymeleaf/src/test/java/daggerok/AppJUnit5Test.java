package daggerok;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("JUnit 5 modern tests")
class AppJUnit5Test {

  @Test
  @DisplayName("Positive test")
  void testPositive() {
    assertThat(true).isTrue();
  }

  @Test
  @DisplayName("Negative test")
  void testNegative() {

    assertThrows(AssertionError.class,
                 () -> assertThat(false).isTrue(),
                 "Assertion error should thrown");
  }
}

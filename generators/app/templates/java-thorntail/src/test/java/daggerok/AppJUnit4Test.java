package daggerok;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AppJUnit4Test {

  @Test
  public void testPositive() {
    assertThat(true, is(true));
  }

  @Test(expected = AssertionError.class)
  public void testNegative() {
    assertThat(false, is(true));
  }
}

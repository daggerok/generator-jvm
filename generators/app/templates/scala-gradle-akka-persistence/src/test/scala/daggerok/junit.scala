package daggerok

/*
  JUnit testing using scala
*/

import org.junit.Assert._
import org.junit._

@Test
class AppTest {

  @Test
  def testOK() = assertTrue(true)

  @Test(expected = classOf[AssertionError])
  def testKO() = assertTrue(false)
}

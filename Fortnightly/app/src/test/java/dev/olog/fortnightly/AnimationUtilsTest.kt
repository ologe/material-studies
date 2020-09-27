package dev.olog.fortnightly

import dev.olog.fortnightly.utils.AnimationUtils.translateToEnd
import dev.olog.fortnightly.utils.AnimationUtils.translateToStart
import org.junit.Assert.fail
import org.junit.Test
import kotlin.math.abs

class AnimationUtilsTest {

    companion object {
        private const val EPSILON = 0.000001
    }

    @Test
    fun testTranslateToEnd() {
        assertAlmost(0f, translateToEnd(0.0f, 0.2f))
        assertAlmost(0f, translateToEnd(0.2f, 0.2f))
        assertAlmost(0.25f, translateToEnd(0.4f, 0.2f))
        assertAlmost(0.5f, translateToEnd(0.6f, 0.2f))
        assertAlmost(0.75f, translateToEnd(0.8f, 0.2f))
        assertAlmost(1f, translateToEnd(1f, 0.2f))
    }

    @Test
    fun testTranslateToStart() {
        assertAlmost(0f, translateToStart(0.0f, 0.4f))
        assertAlmost(0.25f, translateToStart(0.1f, 0.4f))
        assertAlmost(0.5f, translateToStart(0.2f, 0.4f))
        assertAlmost(0.75f, translateToStart(0.3f, 0.4f))
        assertAlmost(1f, translateToStart(0.4f, 0.4f))
        assertAlmost(1f, translateToStart(0.5f, 0.4f))
    }

    // don't care about floating point precision error
    private fun assertAlmost(expected: Float, actual: Float) {
        if (abs(expected - actual) > EPSILON) {
            fail("expected $expected, but was $actual")
        }
    }

}
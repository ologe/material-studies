package dev.olog.material.studies.shared.animation

import androidx.annotation.FloatRange

object AnimationUtils {

    /**
     * @return blend between [min] and [max] based on [fraction]
     */
    fun lerp(
        min: Float,
        max: Float,
        fraction: Float,
    ) = ((1f - fraction) * min + max * fraction).coerceIn(min, max)

    /**
     * @return the fraction of [value] between [min] and [max]
     */
    fun inverseLerp(
        min: Float,
        max: Float,
        value: Float,
    ): Float = (value - min) / (max - min).coerceIn(0f, 1f)

    /**
     * given a [value] between [inMin] and [inMax], returns the value mapped to [outMin] and [outMax]
     */
    fun remap(
        inMin: Float,
        inMax: Float,
        outMin: Float,
        outMax: Float,
        value: Float,
    ): Float {
        val fraction = inverseLerp(inMin, inMax, value)
        return lerp(outMin, outMax, fraction)
    }

    /**
     * Map the values in a way that when:
     * when x < threshold -> return 0
     * when x = threshold -> return 0
     * when x > threshold -> return (0..1) linearly increase from threshold to 1
     *
     * @sample
     * translateToEnd(0f, 0.2f)     -> 0f
     * translateToEnd(0.2f, 0.2f)   -> 0f
     * translateToEnd(0.4f, 0.2f)   -> 0.25f
     * translateToEnd(0.6f, 0.2f)   -> 0.5f
     * translateToEnd(0.8f, 0.2f)   -> 0.75f
     * translateToEnd(1f, 0.2f)     -> 1f
     */
    @FloatRange(from = 0.0, to = 1.0)
    fun translateToEnd(
        @FloatRange(from = 0.0, to = 1.0) x: Float,
        @FloatRange(from = 0.0, to = 1.0) threshold: Float
    ): Float {
        return ((x - threshold) / (1 - threshold)).coerceIn(0f, 1f)
    }

    /**
     * Map the values in a way that when:
     * when x < threshold -> return (0..1) linearly increase from threshold to 1
     * when x = threshold -> return 1
     * when x > threshold -> return 1
     *
     * @see translateToEnd but inverse
     */
    fun translateToStart(
        @FloatRange(from = 0.0, to = 1.0) x: Float,
        @FloatRange(from = 0.0, to = 1.0) threshold: Float
    ): Float {
        return (x / threshold).coerceIn(0f, 1f)
    }

}
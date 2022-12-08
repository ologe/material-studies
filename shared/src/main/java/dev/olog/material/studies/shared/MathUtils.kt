package dev.olog.material.studies.shared

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
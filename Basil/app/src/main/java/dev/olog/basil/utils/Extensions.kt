package dev.olog.basil.utils

operator fun String.times(times: Int): String {
    return buildString {
        repeat(times) {
            append(this@times)
        }
    }
}
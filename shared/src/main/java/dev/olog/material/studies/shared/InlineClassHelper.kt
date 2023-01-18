/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// see https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:compose/ui/ui-util/src/commonMain/kotlin/androidx/compose/ui/util/InlineClassHelper.kt
@file:Suppress("NOTHING_TO_INLINE")

package dev.olog.material.studies.shared

internal inline fun packFloats(val1: Float, val2: Float): Long {
    val v1 = val1.toBits().toLong()
    val v2 = val2.toBits().toLong()
    return v1.shl(32) or (v2 and 0xFFFFFFFF)
}

internal inline fun unpackFloat1(value: Long): Float {
    return Float.fromBits(value.shr(32).toInt())
}

internal inline fun unpackFloat2(value: Long): Float {
    return Float.fromBits(value.and(0xFFFFFFFF).toInt())
}
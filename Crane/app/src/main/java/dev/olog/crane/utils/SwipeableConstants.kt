package dev.olog.crane.utils

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.material.SwipeableConstants

val SwipeableConstants.scrollSpec: AnimationSpec<Float>
    get() = DefaultAnimationSpec

@Suppress("ObjectPropertyName")
private val _animateSpec = tween<Float>()
val SwipeableConstants.animateSpec: AnimationSpec<Float>
    get() = _animateSpec
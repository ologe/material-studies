@file:Suppress("ClassName")

package dev.olog.material.studies

import org.gradle.api.JavaVersion

object config {

    const val minSdk = 23
    const val targetSdk = 33
    const val compileSdk = 33

    const val versionCode = 1_0_0
    const val versionName = "1.0.0"

    val javaVersion = JavaVersion.VERSION_11

}
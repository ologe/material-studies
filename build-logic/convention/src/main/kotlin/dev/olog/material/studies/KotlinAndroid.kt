@file:Suppress("UnstableApiUsage")

package dev.olog.material.studies

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *>,
) = with(commonExtension) {

    compileSdk = config.compileSdk

    defaultConfig {
        minSdk = config.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables.useSupportLibrary = true
    }

    compileOptions {
        sourceCompatibility = config.javaVersion
        targetCompatibility = config.javaVersion
    }

    kotlinOptions {
        jvmTarget = config.javaVersion.toString()
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=androidx.compose.material.ExperimentalMaterialApi",
            "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
            "-opt-in=com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi",
            "-opt-in=com.google.accompanist.pager.ExperimentalPagerApi",
            "-opt-in=androidx.compose.foundation.text.InternalFoundationTextApi",
        )
    }

}
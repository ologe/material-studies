@file:Suppress("UnstableApiUsage")

import dev.olog.material.studies.configureKotlinAndroid
import dev.olog.material.studies.configureLibrary
import dev.olog.material.studies.libraries
import org.gradle.api.Plugin
import org.gradle.api.Project


class LibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
            apply("kotlin-android")
        }

        configureLibrary {
            configureKotlinAndroid(this)

            buildFeatures {
                compose = true
            }

            libraries {
                implementation("kotlin")
                implementation("coroutines")
                implementation("coroutines-android")

                implementation("compose-ui")
                debugImplementation("compose-tooling")
                implementation("compose-tooling-preview")
                implementation("compose-material")
                implementation("compose-activity")
                implementation("compose-icons")
                implementation("compose-constraintLayout")
            }
        }

    }
}
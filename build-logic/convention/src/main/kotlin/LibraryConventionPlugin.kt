@file:Suppress("UnstableApiUsage")

import dev.olog.material.studies.configureKotlinAndroid
import dev.olog.material.studies.configureLibrary
import dev.olog.material.studies.libraries
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType


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

            val catalog = extensions.getByType<VersionCatalogsExtension>().named("libs")
            composeOptions {
                kotlinCompilerExtensionVersion = catalog.findVersion("compose-compiler").get().toString()
            }

            libraries {
                implementation("kotlin")
                implementation("coroutines")
                implementation("coroutines-android")

                implementation("compose-ui")
                implementation("compose-foundation")
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
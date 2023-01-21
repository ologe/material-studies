@file:Suppress("UnstableApiUsage")

import dev.olog.material.studies.config
import dev.olog.material.studies.configureApp
import dev.olog.material.studies.configureKotlinAndroid
import dev.olog.material.studies.libraries
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

class ApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.application")
            apply("kotlin-android")
            apply("kotlin-kapt")
            apply("dagger.hilt.android.plugin")
            apply("kotlinx-serialization")
        }

        configureApp {
            configureKotlinAndroid(this)

            defaultConfig {
                versionCode = config.versionCode
                versionName = config.versionName
                targetSdk = config.targetSdk
            }

            buildFeatures {
                buildConfig = true
                compose = true
            }

            val catalog = extensions.getByType<VersionCatalogsExtension>().named("libs")
            composeOptions {
                kotlinCompilerExtensionVersion = catalog.findVersion("compose-compiler").get().toString()
            }

            libraries {
                project("implementation", ":shared")

                implementation("kotlin")
                implementation("coroutines")
                implementation("coroutines-android")

                implementation("hilt-core")
                kapt("hilt-compiler")

                implementation("androidx-material")
                implementation("compose-ui")
                implementation("compose-foundation")
                debugImplementation("compose-tooling")
                implementation("compose-tooling-preview")
                implementation("compose-material")
                implementation("compose-activity")
                implementation("compose-icons")
                implementation("compose-constraintLayout")

                implementation("accompanist-pager")

                implementation("glide-core")
                implementation("glide-compose")
                kapt("glide-compiler")

                implementation("kotlin-serialization")
            }

        }
    }
}
buildscript {

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(libs.plugin.gradle)
        classpath(libs.plugin.kotlin)
        classpath(libs.plugin.hilt)
    }

}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform") version "1.9.0"
}

repositories {
    mavenCentral()
    maven("../repo")
}

kotlin {
    macosX64()
    macosArm64()

    val commonMain by sourceSets.getting {
        dependencies {
            implementation("org.foo:lib-foo:${project.findProperty("foo.ver") ?: "1.0"}")
            implementation("org.bar:lib-bar:${project.findProperty("bar.ver") ?: "1.0"}")
        }
    }
    val macosMain by sourceSets.creating {
        dependsOn(commonMain)
    }
    val macosX64Main by sourceSets.getting {
        dependsOn(macosMain)
    }
    val macosArm64Main by sourceSets.getting {
        dependsOn(macosMain)
    }

    targets.withType(KotlinNativeTarget::class.java).configureEach {
        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }
}

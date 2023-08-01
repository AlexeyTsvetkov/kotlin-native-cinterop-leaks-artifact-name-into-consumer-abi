import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform") version "1.9.0"
    id("maven-publish")
}

group = "org.foo.internal"
version = project.providers.gradleProperty("deploy.ver").orElse("1.0").get()

repositories {
    mavenCentral()
}

kotlin {
    macosX64()
    macosArm64()

    val commonMain by sourceSets.getting {
        dependencies {
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

    if (project.findProperty("cinterop") != "false") {
        targets.withType(KotlinNativeTarget::class.java).configureEach {
            compilations.getByName("main").cinterops.create("foo-internal") {
                defFileProperty.set(project.file("foo-internal.def"))
            }
        }
    }
}

publishing {
    repositories {
        maven {
            url = uri(layout.projectDirectory.dir("../repo"))
        }
    }
}

tasks.register("publishToRepoDir") {
    dependsOn("publishAllPublicationsToMavenRepository")
}
plugins {
    kotlin("multiplatform") version "1.9.0"
    id("maven-publish")
}

val deployVer = project.findProperty("deploy.ver") ?: "1.0"
group = "org.foo"
version = deployVer

repositories {
    mavenCentral()
    maven("../repo")
}

kotlin {
    macosX64()
    macosArm64()

    val commonMain by sourceSets.getting {
        dependencies {
            implementation("org.foo.internal:lib-foo-internal:${deployVer}")
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
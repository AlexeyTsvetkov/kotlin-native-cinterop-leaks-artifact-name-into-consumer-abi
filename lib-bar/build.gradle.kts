plugins {
    kotlin("multiplatform") version "1.9.0"
    id("maven-publish")
}

group = "org.bar"
version = project.providers.gradleProperty("deploy.ver").orElse("1.0").get()

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
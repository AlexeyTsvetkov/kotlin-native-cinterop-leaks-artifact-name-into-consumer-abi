plugins {
    kotlin("multiplatform") version "1.9.0"
    id("maven-publish")
}

group = "org.foo"
version = project.providers.gradleProperty("deploy.version").orElse("1.0").get()

repositories {
    mavenCentral()
}

kotlin {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        if (project.findProperty("include.cinterop") != "false") {
            iosTarget.compilations.getByName("main").cinterops.create("foo-cinterop") {
                defFileProperty.set(project.file("foo.def"))
            }
        }
    }

    val commonMain by sourceSets.getting
    val commonTest by sourceSets.getting
    val iosMain by sourceSets.creating {
        dependsOn(commonMain)
    }
    val iosTest by sourceSets.creating {
        dependsOn(commonTest)
    }
    val iosX64Main by sourceSets.getting {
        dependsOn(iosMain)
    }
    val iosX64Test by sourceSets.getting {
        dependsOn(iosTest)
    }
    val iosArm64Main by sourceSets.getting {
        dependsOn(iosMain)
    }
    val iosArm64Test by sourceSets.getting {
        dependsOn(iosTest)
    }
    val iosSimulatorArm64Main by sourceSets.getting {
        dependsOn(iosMain)
    }
    val iosSimulatorArm64Test by sourceSets.getting {
        dependsOn(iosTest)
    }
}

publishing {
    repositories {
        maven {
            url = uri(layout.projectDirectory.dir("../repo"))
        }
    }
}

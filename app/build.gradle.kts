plugins {
    kotlin("multiplatform") version "1.9.0"
}

repositories {
    mavenCentral()
    maven("../repo")
}

kotlin {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    val commonMain by sourceSets.getting {
        dependencies {
            implementation("org.foo:lib-foo:1.1")
            implementation("org.bar:lib-bar:1.0")
        }
    }
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

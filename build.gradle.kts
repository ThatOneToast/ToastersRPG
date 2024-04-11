import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    kotlin("jvm")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

val shadowImplementation: Configuration by configurations.creating
configurations["compileOnly"].extendsFrom(shadowImplementation)
configurations["testImplementation"].extendsFrom(shadowImplementation)

group = "dev.toasters.rpg"
version = "1.0.0"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven { url = uri("https://repo.papermc.io/repository/maven-public/") }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
    shadowImplementation("com.github.ToastArgumentative:ToastWonderland:ALPHA-1.0.9-preview2")
    implementation(kotlin("stdlib-jdk8"))
}

tasks {
    assemble {
        dependsOn("sourcesJar")
        dependsOn("shadowJar")
    }

    register<Jar>("sourcesJar") {
        from(sourceSets["main"].allJava)
        archiveClassifier.set("sources")
    }

    shadowJar {
        archiveFileName.set("ToastRpg.jar")
        relocate("com.github.ToastArgumentative", "dev.toastersrpg.lib")
    }

    jar {
        val paths = listOf("C:\\Users\\Faceless\\Desktop\\Servers\\Purpur 1.20.4\\plugins", "/Users/toast/Desktop/testserver/plugins/")
        val existingPath = paths.firstOrNull { File(it).exists() }

        if (existingPath != null) {
            destinationDirectory.set(layout.projectDirectory.dir(existingPath))
        } else {
            destinationDirectory.set(layout.buildDirectory.dir("libs")) // Fallback to default 'libs' directory
        }
    }

    withType(JavaCompile::class.java) {
        options.encoding = "UTF-8"
    }
}
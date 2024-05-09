plugins {
    id("java")
    kotlin("jvm")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "dev.toasters.rpg"
version = "1.0.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven { url = uri("https://repo.papermc.io/repository/maven-public/") }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
    implementation("com.github.ToastArgumentative:ToastWonderland:1.0.9-preview13")
    compileOnly(kotlin("stdlib-jdk8"))
}

tasks {
    assemble {
        dependsOn("shadowJar")
        dependsOn("sourcesJar")
    }

    register<Jar>("sourcesJar") {
        from(sourceSets["main"].allJava)
        archiveClassifier.set("sources")
    }

    shadowJar {
        archiveFileName.set("ToastRpg-$version.jar")

        val paths = listOf ("C:\\Users\\Faceless\\Desktop\\Minecraft DevKit\\Servers\\Paper 1.20.4\\plugins")

        paths.forEach { path ->
            val outputDir = project.file(path)
            destinationDirectory.set(outputDir)
        }
    }

    withType(JavaCompile::class.java) {
        options.encoding = "UTF-8"
    }
}
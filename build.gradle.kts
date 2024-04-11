plugins {
    id("java")
}

group = "dev.toasters.rpg"
version = "1.0-SNAPSHOT"

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
    compileOnly("com.github.ToastArgumentative:ToastWonderland:ALPHA-1.0.9-preview2")
}

tasks {
    assemble {
        dependsOn("sourcesJar")
    }

    register<Jar>("sourcesJar") {
        from(sourceSets["main"].allJava)
        archiveClassifier.set("sources")
    }

    jar {
        //FIXME: Fix path for toast's mac
        val paths = listOf("C:\\Users\\Faceless\\Desktop\\Servers\\Purpur 1.20.4\\plugins", "/Users/toast/Desktop/testserver/plugins")
        paths.forEach { path ->
            val file = File(path)
            if (file.exists()) destinationDirectory.set(file)
        }
    }

    withType(JavaCompile::class.java) {
        options.encoding = "UTF-8"
    }
}
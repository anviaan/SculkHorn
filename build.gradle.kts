plugins {
    id("fabric-loom") version "1.8-SNAPSHOT" apply false
    id("net.neoforged.moddev") version "2.0.49-beta" apply false
}

val MOD_VERSION = project.property("version") as String
val ARCHIVE_NAME = project.property("mod_name") as String
val COMPATIBLE_VERSIONS = project.property("minecraft_version_range") as String

tasks.register("printEnv") {
    doLast {
        val envFile = File(System.getenv("GITHUB_ENV"))
        envFile.appendText("MOD_VERSION=$MOD_VERSION\n")
        envFile.appendText("RELEASE_NAME=$ARCHIVE_NAME-$MOD_VERSION\n")
        envFile.appendText("GAME_VERSIONS=$COMPATIBLE_VERSIONS\n")
    }
}

subprojects {
    repositories {
        maven {
            url = uri("https://libraries.minecraft.net")
            content {
                includeModule("org.lwjgl", "lwjgl-freetype")
            }
        }
    }
}
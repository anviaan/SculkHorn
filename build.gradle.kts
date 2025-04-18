plugins {
    id("fabric-loom") version "1.7-SNAPSHOT" apply false
    id("net.neoforged.moddev") version "0.1.110" apply false
}

val MOD_VERSION = project.property("version") as String
val ARCHIVE_NAME = project.property("mod_name") as String
val COMPATIBLE_VERSIONS = listOf("1.21", "1.21.1")

tasks.register("printEnv") {
    doLast {
        val envFile = File(System.getenv("GITHUB_ENV"))
        envFile.appendText("MOD_VERSION=$MOD_VERSION\n")
        envFile.appendText("RELEASE_NAME=$ARCHIVE_NAME-$MOD_VERSION\n")
        envFile.appendText("GAME_VERSIONS=${COMPATIBLE_VERSIONS.joinToString(",")}\n")
    }
}
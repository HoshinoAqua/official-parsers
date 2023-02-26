plugins{
    kotlin("jvm") version "1.8.10"
    kotlin("plugin.serialization") version "1.8.10"
}

repositories {
    mavenCentral()
}

subprojects {
    apply(plugin = "application")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.serialization")

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    repositories {
        mavenCentral()
        maven(url = "https://jitpack.io")
    }

    dependencies {
        val implementation by configurations
        implementation("com.github.brahmkshatriya:NiceHttp:1.0.0")
        implementation(kotlin("reflect"))
    }
}

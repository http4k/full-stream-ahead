plugins {
    kotlin("jvm") version "2.1.20"
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

val http4kVersion = "6.10.0.0"
val junitVersion = "5.12.0"

allprojects {

    repositories {
        mavenCentral()
    }

    apply(plugin = "org.jetbrains.kotlin.jvm")

    dependencies {
        implementation(platform("org.http4k:http4k-bom:$http4kVersion"))
        implementation("org.http4k:http4k-core")
        implementation("org.http4k:http4k-web-datastar")
        implementation("org.http4k:http4k-server-helidon")
        implementation("org.http4k:http4k-server-undertow")
        implementation("org.http4k:http4k-server-jetty")
        implementation("org.http4k:http4k-format-moshi")
        implementation("org.http4k:http4k-template-handlebars")
        implementation("dev.forkhandles:time4k:2.22.2.1")

        testImplementation(platform("org.junit:junit-bom:$junitVersion"))
        testImplementation("org.junit.platform:junit-platform-launcher")
        testImplementation("org.junit.jupiter:junit-jupiter-api")
        testImplementation("org.junit.jupiter:junit-jupiter-engine")
        testImplementation("org.http4k:http4k-testing-hamkrest")
        testImplementation("com.natpryce:hamkrest:_")
    }

    tasks.test {
        useJUnitPlatform()
    }
}

plugins {
    kotlin("jvm") version "2.0.21"
    application
}

repositories {
    mavenLocal()
    mavenCentral()
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

val http4kVersion = "5.35.5.0"
val junitVersion = providers.gradleProperty("junitVersion").orNull

dependencies {
    implementation(platform("org.http4k:http4k-bom:$http4kVersion"))
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-web-datastar:$http4kVersion")
    implementation("org.http4k:http4k-server-helidon")

    implementation("com.github.luben:zstd-jni:1.5.6-8")
    implementation("org.http4k:http4k-format-moshi")

    implementation("org.http4k:http4k-template-handlebars")
}

application {
    mainClass.set("com.example.HotwireKt")
}

tasks.test {
    useJUnitPlatform()
}

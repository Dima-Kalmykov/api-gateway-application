plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")

    kotlin("jvm")
    kotlin("kapt")
    kotlin("plugin.spring")
}

repositories {
    mavenCentral()
}

dependencies {

    // Spring
    implementation(group = "org.springframework.boot", name = "spring-boot-starter")
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-web")
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-actuator")

    // Utils
    implementation(group = "org.jetbrains.kotlin", name = "kotlin-reflect")
    implementation(group = "org.jetbrains.kotlin", name = "kotlin-stdlib-jdk8")
    implementation(group = "com.fasterxml.jackson.module", name = "jackson-module-kotlin")

    // Annotation processor
    kapt(group = "org.springframework.boot", name = "spring-boot-configuration-processor")
    annotationProcessor(group = "org.springframework.boot", name = "spring-boot-configuration-processor")

    // Open API
    val openApiVersion = properties["openApiVersion"] as String
    implementation(group = "org.springdoc", name = "springdoc-openapi-ui", version = openApiVersion)

    // Logging
    val loggingVersion = properties["kotlinLoggingVersion"] as String
    implementation(group = "io.github.microutils", name = "kotlin-logging", version = loggingVersion)

    // Test
    testImplementation(group = "org.springframework.boot", name = "spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}

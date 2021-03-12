import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinLoggingVersion = "1.8.0.1"
val bootstrapVersion = "4.5.0"
val jqueryVersion = "3.5.1"
val popperVersion = "1.12.9-1"
val webjarsLocatorVersion = "0.40"
val dataTablesVersion = "1.10.21"
val openIconicVersion = "1.1.1"
val mockitoVersion = "3.8.0"

plugins {
    val kotlinVersion = "1.4.31"
    id("org.springframework.boot") version "2.4.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.jetbrains.kotlin.plugin.jpa") version kotlinVersion
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("kapt") version kotlinVersion
}

group = "com.irotsoma"
version = "0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    //spring boot
    implementation("org.springframework.boot:spring-boot-starter-mustache")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    //kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    //database
    implementation("org.liquibase:liquibase-core")
    implementation( "org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.h2database:h2")
    //logging
    implementation( "io.github.microutils:kotlin-logging:$kotlinLoggingVersion")
    //javascript webjars
    implementation("org.webjars:bootstrap:$bootstrapVersion")
    implementation("org.webjars:jquery:$jqueryVersion")
    implementation("org.webjars:popper.js:$popperVersion")
    implementation("org.webjars:webjars-locator:$webjarsLocatorVersion")
    implementation("org.webjars:datatables:$dataTablesVersion")
    //devtools
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    //test tools
    testImplementation("org.mockito:mockito-core:$mockitoVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
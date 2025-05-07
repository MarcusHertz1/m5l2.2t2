plugins {
    kotlin("jvm") version "2.1.20"
    jacoco
}

group = "ru.netology"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //testImplementation(kotlin("test"))
    testImplementation ("junit:junit:4.13.2")
}
/*
tasks.test {
    useJUnitPlatform()
}*/
kotlin {
    jvmToolchain(22)
}
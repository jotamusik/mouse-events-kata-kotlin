import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.8.21"
}

group = "dev.kata"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
  maven { url = uri("https://dl.bintray.com/kotlin/kotlinx/") }
  maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
}

dependencies {
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.6.4")
  implementation("io.reactivex.rxjava3:rxjava:3.1.6")
  implementation("io.reactivex.rxjava3:rxkotlin:3.0.1")
  testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
  testImplementation(kotlin("test"))
  testImplementation("org.assertj:assertj-core:3.22.0")
  testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
  testImplementation("org.mockito:mockito-inline:4.5.1")
  testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
}

tasks.test {
  useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = "1.8"
}

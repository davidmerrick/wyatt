import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.11"
    id("de.fayard.buildSrcVersions") version "0.3.2"
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation(Libs.kotlin_stdlib_jdk8)
    implementation(Libs.selenium_java)
    implementation(Libs.selenide)
    implementation(Libs.koin_core)
    implementation(Libs.okhttp)
    implementation(Libs.aws_lambda_java_core)
    implementation(Libs.aws_lambda_java_events)

    testImplementation(Libs.koin_test)
    testImplementation(Libs.kotlin_test)
    testImplementation(Libs.kotlin_test_junit)
    testImplementation(Libs.testng)
}

val deployDev = tasks.create<Exec>("deployDev") {
    commandLine = listOf("serverless", "deploy", "--stage=dev")
}

val deployPrd = tasks.create<Exec>("deployPrd") {
    commandLine = listOf("serverless", "deploy", "--stage=prd")
}

// Alias for deploy dev
val deploy = tasks.create("deploy")
deploy.dependsOn(deployDev)

deployDev.dependsOn(tasks.getByName("shadowJar"))
deployPrd.dependsOn(tasks.getByName("shadowJar"))

tasks.test {
    useTestNG()
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
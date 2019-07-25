import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.merricklabs.wyatt"

plugins {
    id("org.jetbrains.kotlin.jvm") version Versions.org_jetbrains_kotlin
    id("de.fayard.buildSrcVersions") version Versions.de_fayard_buildsrcversions_gradle_plugin
    id("com.github.johnrengelman.shadow") version Versions.com_github_johnrengelman_shadow_gradle_plugin
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
    implementation(Libs.aws_java_sdk_s3)
    implementation(Libs.jackson_core)
    implementation(Libs.jackson_databind)
    implementation(Libs.jackson_module_kotlin)
    implementation(Libs.kotlin_logging)

    testImplementation(Libs.koin_test)
    testImplementation(Libs.kotlin_test)
    testImplementation(Libs.kotlin_test_junit)
    testImplementation(Libs.kotlintest_runner_junit5)
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
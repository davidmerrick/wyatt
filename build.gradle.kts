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
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.seleniumhq.selenium:selenium-java:3.141.59")
    implementation(Libs.selenide)
    implementation(Libs.koin_core)
    implementation(Libs.okhttp)
    implementation("com.amazonaws:aws-lambda-java-core:1.2.0")
    implementation("com.amazonaws:aws-lambda-java-events:2.2.6")

    testImplementation("org.koin:koin-test:2.0.1")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("org.testng:testng:6.14.3")
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
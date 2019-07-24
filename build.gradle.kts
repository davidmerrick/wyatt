plugins {
    id("org.jetbrains.kotlin.jvm").version("1.3.11")
    application
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.seleniumhq.selenium:selenium-java:3.141.59")
    implementation("com.codeborne:selenide:5.2.6")
    implementation("org.koin:koin-core:2.0.1")
    implementation("com.squareup.okhttp3:okhttp:3.14.2")
    implementation("com.amazonaws:aws-lambda-java-core:1.2.0")
    implementation("com.amazonaws:aws-lambda-java-events:2.2.6")

    testImplementation("org.koin:koin-test:2.0.1")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("org.testng:testng:6.14.3")
}

application {
    mainClassName = "com.merricklabs.verizon.AppKt"
}

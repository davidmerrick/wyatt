group = "com.merricklabs.wyatt"

plugins {
    kotlin("jvm") version Versions.org_jetbrains_kotlin
    id("de.fayard.buildSrcVersions") version Versions.de_fayard_buildsrcversions_gradle_plugin
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
    implementation(Libs.slf4j_api)
    implementation(Libs.slf4j_jdk14)
    implementation(Libs.aws_lambda_java_core)
    implementation(Libs.aws_lambda_java_events)
    implementation(Libs.aws_java_sdk_s3)
    implementation(Libs.jackson_core)
    implementation(Libs.jackson_databind)
    implementation(Libs.jackson_module_kotlin)
    implementation(Libs.kotlin_logging)
    implementation(Libs.awaitility)

    testImplementation(Libs.koin_test)
    testImplementation(Libs.kotlin_test)
    testImplementation(Libs.kotlin_test_junit)
    testImplementation(Libs.kotlintest_runner_junit5)
    testImplementation(Libs.testng)
    testImplementation(Libs.mockito_kotlin)
}

tasks {

    val deployPrd by creating(Exec::class) {
        commandLine = listOf("serverless", "deploy", "--stage=prd")
    }

    val deployDev by creating(Exec::class) {
        commandLine = listOf("serverless", "deploy", "--stage=dev")
    }

    val deploy by creating {
        dependsOn(deployDev)
    }

    test {
        useTestNG()
    }

    val buildZip by creating(Zip::class) {
        from(compileKotlin)
        from(processResources)
        into("lib") {
            from(zipTree("lib/lib.zip"))
            from(configurations.runtimeClasspath)
        }
    }

    build {
        dependsOn(buildZip)
    }

    compileKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}
/**
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version. */
object Versions {
    const val aws_java_sdk_s3: String = "1.11.597" // available: "1.11.598"

    const val slf4j_api: String = "1.7.26"

    const val slf4j_jdk14: String = "1.7.26"

    const val aws_lambda_java_core: String = "1.2.0"

    const val aws_lambda_java_events: String = "2.2.6"

    const val selenide: String = "5.2.6"

    const val com_fasterxml_jackson_core: String = "2.9.9" // available: "2.10.0.pr1"

    const val jackson_module_kotlin: String = "2.9.9" // available: "2.10.0.pr1"

    const val com_github_johnrengelman_shadow_gradle_plugin: String = "5.0.0"
    // available: "5.1.0"

    const val kotlin_logging: String = "1.6.26"

    const val mockito_kotlin: String = "2.1.0"

    const val okhttp: String = "3.14.2" // available: "4.0.1"

    const val de_fayard_buildsrcversions_gradle_plugin: String = "0.3.2"

    const val kotlintest_runner_junit5: String = "3.3.2" // available: "3.4.0"

    const val org_jetbrains_kotlin_jvm_gradle_plugin: String = "1.3.41"

    const val org_jetbrains_kotlin: String = "1.3.41"

    const val org_koin: String = "2.0.1"

    const val selenium_java: String = "3.141.59"

    const val testng: String = "6.14.3"

    /**
     *
     *   To update Gradle, edit the wrapper file at path:
     *      ./gradle/wrapper/gradle-wrapper.properties
     */
    object Gradle {
        const val runningVersion: String = "5.1.1"

        const val currentVersion: String = "5.5.1"

        const val nightlyVersion: String = "5.7-20190724220115+0000"

        const val releaseCandidate: String = ""
    }
}

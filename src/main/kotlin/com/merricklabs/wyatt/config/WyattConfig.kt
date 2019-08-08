package com.merricklabs.wyatt.config

import org.koin.core.KoinComponent

const val DEFAULT_REGION = "us-west-2"
const val DEFAULT_BUCKET_NAME = "wyatt-bills"
const val DEFAULT_ERRORS_BUCKET_NAME = "wyatt-errors"

class WyattConfig : KoinComponent {
    val s3BucketName: String = System.getenv("S3_BUCKET_NAME") ?: DEFAULT_BUCKET_NAME
    val errorsBucketName: String = System.getenv("ERRORS_BUCKET_NAME") ?: DEFAULT_ERRORS_BUCKET_NAME
    val awsRegion: String = System.getenv("AWS_REGION") ?: DEFAULT_REGION
    val verizon = Verizon()

    class Verizon {
        val username: String = System.getenv("VERIZON_USERNAME")
        val password: String = System.getenv("VERIZON_PASSWORD")

        // Verizon accounts only have one security question
        val securityAnswer: String = System.getenv("SECURITY_ANSWER")
    }
}
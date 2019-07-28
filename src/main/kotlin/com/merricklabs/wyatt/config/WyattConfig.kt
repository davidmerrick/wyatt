package com.merricklabs.wyatt.config

import org.koin.core.KoinComponent

const val DEFAULT_REGION = "us-west-2"
const val DEFAULT_BUCKET_NAME = "wyatt-bills"

class WyattConfig : KoinComponent {
    val s3BucketName: String = System.getenv("S3_BUCKET_NAME") ?: DEFAULT_BUCKET_NAME
    val awsRegion: String = System.getenv("AWS_REGION") ?: DEFAULT_REGION
    val verizon = Verizon()

    class Verizon {
        val username: String = System.getenv("VERIZON_USERNAME")
        val password: String = System.getenv("VERIZON_PASSWORD")
        val securityAnswer1: String = System.getenv("SECURITY_ANSWER_1")
    }
}
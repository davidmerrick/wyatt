package com.merricklabs.wyatt.external.aws

import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.PutObjectRequest
import com.merricklabs.wyatt.config.WyattConfig
import mu.KotlinLogging
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.io.File

private val log = KotlinLogging.logger {}

class WyattS3Client : KoinComponent {
    private val config by inject<WyattConfig>()
    private val s3: AmazonS3

    init {
        val builder = AmazonS3Client.builder()
        builder.region = config.awsRegion
        config.s3Endpoint?.let {
            builder.setEndpointConfiguration(AwsClientBuilder.EndpointConfiguration(it, config.awsRegion))
        }
        s3 = builder.build()
    }

    fun uploadFile(file: File, fileName: String) {
        val request = PutObjectRequest(config.s3BucketName, fileName, file)
        val result = s3.putObject(request)
        log.info("Uploaded object")
    }
}
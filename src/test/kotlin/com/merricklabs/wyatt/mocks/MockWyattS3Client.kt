package com.merricklabs.wyatt.mocks

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3Client
import com.merricklabs.wyatt.external.aws.WyattS3ClientImpl

class MockWyattS3Client : WyattS3ClientImpl() {
    override fun buildS3(): AmazonS3 {
        val credentials = BasicAWSCredentials("XXXXX", "XXXXX")
        val builder = AmazonS3Client.builder()
                .withPathStyleAccessEnabled(true)
                .withCredentials(AWSStaticCredentialsProvider(credentials))

        config.s3Endpoint?.let {
            builder.setEndpointConfiguration(AwsClientBuilder.EndpointConfiguration(it, config.awsRegion))
        }

        return builder.build()
    }
}
package com.merricklabs.wyatt.external.aws

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3Client
import com.merricklabs.wyatt.config.WyattConfig
import mu.KotlinLogging
import org.koin.core.KoinComponent
import org.koin.core.inject

private val log = KotlinLogging.logger {}

open class WyattS3ClientImpl : KoinComponent, WyattS3Client {
    protected val config by inject<WyattConfig>()
    override val s3 = buildS3()

    protected open fun buildS3(): AmazonS3 {
        return AmazonS3Client.builder().build()
    }
}
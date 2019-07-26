package com.merricklabs.wyatt.handlers.logic

import com.merricklabs.wyatt.config.WyattConfig
import com.merricklabs.wyatt.external.aws.WyattS3Client
import mu.KotlinLogging
import org.koin.core.KoinComponent
import org.koin.core.inject

private val log = KotlinLogging.logger {}

class WyattLogic : KoinComponent {
    private val config by inject<WyattConfig>()
    private val wyattS3Client by inject<WyattS3Client>()

    fun handleRequest() {
        log.info("Received event")
        wyattS3Client.s3.putObject(config.s3BucketName, "foo", "banana")
    }
}
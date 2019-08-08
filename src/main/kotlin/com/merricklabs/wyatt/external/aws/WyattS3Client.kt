package com.merricklabs.wyatt.external.aws

import com.amazonaws.services.s3.AmazonS3
import com.merricklabs.wyatt.config.WyattConfig
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.io.File
import java.time.LocalDateTime

abstract class WyattS3Client : KoinComponent {
    private val config by inject<WyattConfig>()

    protected abstract val s3: AmazonS3

    fun uploadBill(billSrc: String) {
        s3.putObject(config.s3BucketName, getBillFileName(), billSrc)
    }

    fun uploadScreenshot(screenshot: File) {
        s3.putObject(config.errorsBucketName, "screenshot-${LocalDateTime.now()}.png", screenshot)
    }

    private fun getBillFileName(): String {
        val now = LocalDateTime.now()
        val month = now.month.name.toLowerCase()
        val year = now.year
        return "bill-$month-$year.json"
    }

    fun createBuckets() {
        this.s3.createBucket(config.s3BucketName)
        this.s3.createBucket(config.errorsBucketName)
    }

    fun deleteBuckets() {
        this.s3.forceDeleteBucket(config.s3BucketName)
        this.s3.forceDeleteBucket(config.errorsBucketName)
    }
}
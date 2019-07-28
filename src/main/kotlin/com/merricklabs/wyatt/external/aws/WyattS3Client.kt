package com.merricklabs.wyatt.external.aws

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.Bucket
import com.merricklabs.wyatt.config.WyattConfig
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.time.LocalDateTime

abstract class WyattS3Client : KoinComponent {
    private val config by inject<WyattConfig>()

    protected abstract val s3: AmazonS3

    fun uploadBill(billSrc: String) {
        s3.putObject(config.s3BucketName, getBillFileName(), billSrc)
    }

    private fun getBillFileName(): String {
        val now = LocalDateTime.now()
        val month = now.month.name.toLowerCase()
        val year = now.year
        return "bill-$month-$year.json"
    }

    fun createBucket(): Bucket = this.s3.createBucket(config.s3BucketName)
    fun deleteBucket() = this.s3.forceDeleteBucket(config.s3BucketName)
}
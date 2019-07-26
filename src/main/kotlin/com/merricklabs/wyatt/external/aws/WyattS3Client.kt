package com.merricklabs.wyatt.external.aws

import com.amazonaws.services.s3.AmazonS3

interface WyattS3Client {
    val s3: AmazonS3
}
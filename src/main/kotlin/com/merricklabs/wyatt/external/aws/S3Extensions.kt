package com.merricklabs.wyatt.external.aws

import com.amazonaws.services.s3.AmazonS3

fun AmazonS3.forceDeleteBucket(bucketName: String) {
    var objectListing = this.listObjects(bucketName)
    while (true) {
        val objIter = objectListing.getObjectSummaries().iterator()
        while (objIter.hasNext()) {
            this.deleteObject(bucketName, objIter.next().key)
        }

        if (objectListing.isTruncated) {
            objectListing = this.listNextBatchOfObjects(objectListing)
        } else {
            break
        }
    }
}
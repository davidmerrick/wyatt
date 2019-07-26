package com.merricklabs.wyatt.external.aws

import com.google.common.io.Resources
import com.merricklabs.wyatt.DEFAULT_BUCKET_NAME
import com.merricklabs.wyatt.IntegrationTestBase
import org.koin.test.get
import org.testng.annotations.Test
import java.io.File

class WyattS3ClientTest : IntegrationTestBase() {

    @Test
    fun `Upload a single file`() {
        val file = File(Resources.getResource("test_bill.json").toURI())
        val wyattS3Client = get<WyattS3Client>()
        wyattS3Client.s3.putObject(DEFAULT_BUCKET_NAME, "foo", file)
    }
}
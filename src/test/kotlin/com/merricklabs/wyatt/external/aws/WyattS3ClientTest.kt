package com.merricklabs.wyatt.external.aws

import com.google.common.io.Resources
import com.merricklabs.wyatt.INTEGRATION_GROUP
import com.merricklabs.wyatt.IntegrationTestBase
import com.merricklabs.wyatt.config.WyattConfig
import org.koin.test.get
import org.testng.annotations.Test
import java.io.File

class WyattS3ClientTest : IntegrationTestBase() {

    @Test(groups = [INTEGRATION_GROUP])
    fun `Upload a single file`() {
        val config = get<WyattConfig>()
        val file = File(Resources.getResource("test_bill.json").toURI())
        val wyattS3Client = get<WyattS3Client>()
        wyattS3Client.s3.putObject(config.s3BucketName, "foo", file)
    }
}
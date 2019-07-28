package com.merricklabs.wyatt.external.aws

import com.google.common.io.Resources
import com.merricklabs.wyatt.INTEGRATION_GROUP
import com.merricklabs.wyatt.IntegrationTestBase
import org.koin.test.get
import org.testng.annotations.Test

class WyattS3ClientTest : IntegrationTestBase() {

    @Test(groups = [INTEGRATION_GROUP])
    fun `Upload a single file`() {
        val fileContent: String = Resources.getResource("test_bill.json").readText()
        val wyattS3Client = get<WyattS3Client>()
        wyattS3Client.uploadBill(fileContent)
    }
}
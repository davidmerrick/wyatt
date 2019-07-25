package com.merricklabs.wyatt.models.verizon

import com.google.common.io.Resources
import com.merricklabs.wyatt.IntegrationTestBase
import com.merricklabs.wyatt.util.WyattObjectMapper
import io.kotlintest.shouldNotBe
import org.koin.test.get
import org.testng.annotations.Test

object VerizonBillTest : IntegrationTestBase() {

    @Test
    fun `Deserialize Verizon bill`() {
        val mapper = get<WyattObjectMapper>()
        val fileContent: String = Resources.getResource("test_bill.json").readText()
        val deserialized = mapper.readValue(fileContent, VerizonBill::class.java)
        deserialized shouldNotBe null
    }

}

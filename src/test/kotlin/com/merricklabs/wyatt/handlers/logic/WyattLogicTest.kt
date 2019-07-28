package com.merricklabs.wyatt.handlers.logic

import com.merricklabs.wyatt.IntegrationTestBase
import org.koin.test.get
import org.testng.annotations.Test

class WyattLogicTest : IntegrationTestBase() {

    @Test(enabled = false)
    fun `Test logic`() {
        val logic = get<WyattLogic>()
        logic.handleRequest()
    }
}

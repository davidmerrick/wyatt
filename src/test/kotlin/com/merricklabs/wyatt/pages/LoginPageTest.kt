package com.merricklabs.wyatt.pages

import com.merricklabs.wyatt.IntegrationTestBase
import com.merricklabs.wyatt.external.verizon.VerizonClient
import org.koin.test.get
import org.testng.annotations.Test

class LoginPageTest : IntegrationTestBase() {

    @Test(enabled = false)
    fun `Log in to website`() {
        val loginPage = get<LoginPage>()
        loginPage.goto()
        loginPage.enterUsername(System.getenv("USERNAME"))
        loginPage.enterPassword(System.getenv("PASSWORD"))
        loginPage.submit()

        Thread.sleep(3000)
        val verizonClient = get<VerizonClient>()
        verizonClient.fetchBill()
    }
}
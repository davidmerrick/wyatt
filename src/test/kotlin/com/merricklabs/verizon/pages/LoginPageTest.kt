package com.merricklabs.verizon.pages

import com.merricklabs.verizon.IntegrationTestBase
import com.merricklabs.verizon.handlers.external.VerizonClient
import com.merricklabs.verizon.handlers.util.ChromeWebService
import org.koin.test.get
import org.testng.annotations.Test

class LoginPageTest: IntegrationTestBase(){


    @Test
    fun `Log in to website`(){
        val loginPage = get<LoginPage>()
        loginPage.goto()
        loginPage.enterUsername(System.getenv("USERNAME"))
        loginPage.enterPassword(System.getenv("PASSWORD"))
        loginPage.submit()

        Thread.sleep(3000)
        val webService = get<ChromeWebService>()
        val verizonClient = get<VerizonClient>()
        verizonClient.fetchBillJson(webService.driver.manage().cookies)
    }
}
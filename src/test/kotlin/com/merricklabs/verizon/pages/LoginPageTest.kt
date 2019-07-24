package com.merricklabs.verizon.pages

import com.merricklabs.verizon.IntegrationTestBase
import org.koin.test.get
import org.testng.annotations.Test

class LoginPageTest: IntegrationTestBase(){


    @Test
    fun `Log in to website`(){
        val loginPage = get<LoginPage>()
        loginPage.goto()
        loginPage.enterUsername(System.getenv("USERNAME") ?: "foo")
        loginPage.enterPassword(System.getenv("PASSWORD") ?: "bar")
        loginPage.submit()

        Thread.sleep(5000)
    }
}
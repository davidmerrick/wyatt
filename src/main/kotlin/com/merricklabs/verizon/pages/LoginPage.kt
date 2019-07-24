package com.merricklabs.verizon.pages

import com.merricklabs.verizon.handlers.util.ChromeWebService
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class LoginPage : KoinComponent {

    private val webService by inject<ChromeWebService>()

    fun goto() {
        webService.driver.get(LOGIN_URL)
    }

    fun enterUsername(username: String) {
        val selector = By.cssSelector(USERNAME_SELECTOR)
        WebDriverWait(webService.driver, 5)
                .until(ExpectedConditions.elementToBeClickable(selector))
        val field = webService.driver.findElement(selector)
        field.click()
        field.sendKeys(username)
    }

    fun enterPassword(password: String) {
        val selector = By.cssSelector(PASSWORD_SELECTOR)
        WebDriverWait(webService.driver, 5)
                .until(ExpectedConditions.elementToBeClickable(selector))
        val field = webService.driver.findElement(selector)
        field.click()
        field.sendKeys(password)
    }

    fun submit() {
        val selector = By.cssSelector(SUBMIT_SELECTOR)
        WebDriverWait(webService.driver, 5)
                .until(ExpectedConditions.elementToBeClickable(selector))
        val field = webService.driver.findElement(selector)
        field.click()
    }

    private companion object {
        const val LOGIN_URL = "https://login.verizonwireless.com/vzauth/UI/Login?userNameOnly=false&mode=i&realm=vzw&customerType=DO"
        const val USERNAME_SELECTOR = "input#IDToken1"
        const val PASSWORD_SELECTOR = "input#IDToken2"
        const val SUBMIT_SELECTOR = "button#login-submit"
    }
}

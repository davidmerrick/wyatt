package com.merricklabs.verizon.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class LoginPage(private val browser: WebDriver) {
    fun goto() {
        browser.get(LOGIN_URL)
    }

    fun enterUsername(username: String) {
        val usernameSelector = By.cssSelector("input#$USERNAME_FIELD_ID")
        WebDriverWait(browser, 5)
                .until(ExpectedConditions.elementToBeClickable(usernameSelector))
        val field = browser.findElement(usernameSelector)
        field.click()
        field.sendKeys(username)
    }

    fun enterPassword(password: String) {
        val usernameSelector = By.cssSelector("input#$PASSWORD_FIELD_ID")
        WebDriverWait(browser, 5)
                .until(ExpectedConditions.elementToBeClickable(usernameSelector))
        val field = browser.findElement(usernameSelector)
        field.click()
        field.sendKeys(password)
    }

    private companion object {
        const val LOGIN_URL = "https://login.verizonwireless.com/vzauth/UI/Login?userNameOnly=false&mode=i&realm=vzw&customerType=DO"
        const val USERNAME_FIELD_ID = "IDToken1"
        const val PASSWORD_FIELD_ID = "IDToken2"
    }
}

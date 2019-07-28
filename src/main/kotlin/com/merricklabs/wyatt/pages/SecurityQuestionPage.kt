package com.merricklabs.wyatt.pages

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class SecurityQuestionPage : WyattPage() {

    override val pageLoadHook = {
        driver.findElement(By.cssSelector(SUBMIT_BUTTON_SELECTOR)).isDisplayed
    }

    fun getSecurityQuestion(): String {
        return driver.findElement(By.cssSelector("label[for=IDToken1]")).text
    }

    fun enterSecurityQuestion(value: String) {
        val selector = By.cssSelector(SECURITY_QUESTION_SELECTOR)
        WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(selector))
        val field = driver.findElement(selector)
        field.click()
        field.sendKeys(value)
    }

    fun submit() {
        return driver.findElement(By.cssSelector(SUBMIT_BUTTON_SELECTOR)).click()
    }

    private companion object {
        const val SECURITY_QUESTION_SELECTOR = "input#IDToken1"
        const val SUBMIT_BUTTON_SELECTOR = "button#otherButton"
    }
}

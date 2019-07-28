package com.merricklabs.wyatt.pages

import mu.KotlinLogging
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

private val log = KotlinLogging.logger {}

class SecurityQuestionPage : WyattPage() {

    override val pageLoadHook = {
        driver.findElement(By.cssSelector(SUBMIT_BUTTON_SELECTOR)).isDisplayed
    }

    fun getSecurityQuestion(): String {
        return driver.findElement(By.cssSelector("label[for=IDToken1]")).text
    }

    fun enterSecurityAnswer(value: String) {
        log.info("Entering security answer")
        val selector = By.cssSelector(SECURITY_QUESTION_SELECTOR)
        WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(selector))
        val field = driver.findElement(selector)
        field.click()
        field.sendKeys(value)
    }

    fun submit() {
        log.info("Submitting security question form")
        return driver.findElement(By.cssSelector(SUBMIT_BUTTON_SELECTOR)).click()
    }

    private companion object {
        const val SECURITY_QUESTION_SELECTOR = "input#IDToken1"
        const val SUBMIT_BUTTON_SELECTOR = "button#otherButton"
    }
}

package com.merricklabs.wyatt.handlers.util

import org.koin.core.KoinComponent
import org.openqa.selenium.chrome.ChromeDriver

class ChromeWebService : KoinComponent {

    val driver by lazy {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver-mac")
        ChromeDriver()
    }
}
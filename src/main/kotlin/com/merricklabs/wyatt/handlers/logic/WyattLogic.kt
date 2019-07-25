package com.merricklabs.wyatt.handlers.logic

import mu.KotlinLogging
import org.koin.core.KoinComponent

private val log = KotlinLogging.logger {}

class WyattLogic : KoinComponent {
    fun handleRequest() {
        log.info("Received event")
    }
}
package com.merricklabs.wyatt.handlers.logic

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent
import mu.KotlinLogging
import org.koin.core.KoinComponent

private val log = KotlinLogging.logger {}

class WyattLogic : KoinComponent {
    fun handleRequest(input: ScheduledEvent, context: Context) {
        log.info("Received event")
    }
}
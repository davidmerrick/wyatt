package com.merricklabs.wyatt.handlers

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent
import com.merricklabs.wyatt.WyattModule
import com.merricklabs.wyatt.handlers.logic.WyattLogic
import org.koin.core.context.startKoin

class WyattHandler : RequestHandler<ScheduledEvent, Unit> {

    private val logic: WyattLogic

    init {
        startKoin {
            modules(WyattModule)
        }

        logic = WyattLogic()
    }

    override fun handleRequest(input: ScheduledEvent, context: Context) {
        return logic.handleRequest()
    }
}
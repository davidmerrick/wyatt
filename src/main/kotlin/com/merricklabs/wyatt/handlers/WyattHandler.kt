package com.merricklabs.wyatt.handlers

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.merricklabs.wyatt.WyattModule
import com.merricklabs.wyatt.handlers.logic.WyattLogic
import org.koin.core.context.startKoin

class WyattHandler : RequestHandler<Any, Unit> {

    private val logic: WyattLogic

    init {
        startKoin {
            modules(WyattModule)
        }

        logic = WyattLogic()
    }

    override fun handleRequest(input: Any, context: Context) {
        return logic.handleRequest()
    }
}
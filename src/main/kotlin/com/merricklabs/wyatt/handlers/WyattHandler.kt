package com.merricklabs.wyatt.handlers

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import com.merricklabs.wyatt.WyattModule
import com.merricklabs.wyatt.handlers.logic.WyattLogic
import org.koin.core.context.startKoin

class WyattHandler : RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private val logic: WyattLogic

    init {
        startKoin {
            modules(WyattModule)
        }

        logic = WyattLogic()
    }

    override fun handleRequest(input: APIGatewayProxyRequestEvent, context: Context): APIGatewayProxyResponseEvent {
        return logic.handleRequest(input, context)
    }
}
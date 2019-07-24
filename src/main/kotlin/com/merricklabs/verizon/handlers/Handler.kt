package com.merricklabs.verizon.handlers

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import com.merricklabs.verizon.BillFetcherModule
import com.merricklabs.verizon.handlers.logic.BillFetcherLogic
import org.koin.core.context.startKoin

class Handler : RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private val logic: BillFetcherLogic

    init {
        startKoin {
            modules(BillFetcherModule)
        }

        logic = BillFetcherLogic()
    }

    override fun handleRequest(input: APIGatewayProxyRequestEvent, context: Context): APIGatewayProxyResponseEvent {
        return logic.handleRequest(input, context)
    }
}
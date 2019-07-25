package com.merricklabs.wyatt.handlers.logic

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent
import com.merricklabs.wyatt.IntegrationTestBase
import org.koin.test.get
import org.mockito.Mockito
import org.testng.annotations.Test

class WyattLogicTest : IntegrationTestBase() {

    private val mockEvent = Mockito.mock(ScheduledEvent::class.java)
    private val mockContext = Mockito.mock(Context::class.java)

    @Test
    fun `Test logic`() {
        val logic = get<WyattLogic>()
        logic.handleRequest(mockEvent, mockContext)
    }
}

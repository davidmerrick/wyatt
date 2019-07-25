package com.merricklabs.wyatt.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

class WyattObjectMapper : ObjectMapper() {

    init {
        this.registerModule(KotlinModule())
    }

}
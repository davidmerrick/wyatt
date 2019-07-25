package com.merricklabs.wyatt.models.verizon

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Model of a Verizon bill
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class VerizonBill(
        @JsonProperty("data") private val data: BillData
)
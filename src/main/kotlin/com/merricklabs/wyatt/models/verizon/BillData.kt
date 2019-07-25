package com.merricklabs.wyatt.models.verizon

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class BillData(
        @JsonProperty("changes_new") private val changes_new: Map<String, Any>,
        @JsonProperty("bill") private val bill: Map<String, Any>,
        @JsonProperty("lineLevelDetails") private val lineLevelDetails: List<Any>,
        @JsonProperty("accountSummaryDetails") private val accountSummaryDetails: List<Any>
)
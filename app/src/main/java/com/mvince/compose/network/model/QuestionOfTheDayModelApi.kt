package com.mvince.compose.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuestionOfTheDayModelApi(
    @Json(name = "response_code")
    val responseCode: Int, // 0
    @Json(name = "results")
    val results: List<Result>
)
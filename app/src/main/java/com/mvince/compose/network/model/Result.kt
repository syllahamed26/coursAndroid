package com.mvince.compose.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "category")
    val category: String = "", // General Knowledge
    @Json(name = "correct_answer")
    val correctAnswer: String = "", // HTC
    @Json(name = "difficulty")
    val difficulty: String = "", // easy
    @Json(name = "incorrect_answers")
    val incorrectAnswers: List<String> = listOf(),
    @Json(name = "question")
    val question: String = "", // Which company did Valve cooperate with in the creation of the Vive?
    @Json(name = "type")
    val type: String = "" // multiple
)
package com.mvince.compose.domain

import com.mvince.compose.network.model.Result

class QuestionFirebase (
    val questions: List<Result> = emptyList(),
    val date: String? = null
//    val date: Date = Date()
)
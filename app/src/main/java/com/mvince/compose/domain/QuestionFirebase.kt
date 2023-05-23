package com.mvince.compose.domain

import com.mvince.compose.network.model.Result
import java.util.Date

class QuestionFirebase (
    val questions: List<Result> = emptyList(),
    val date: Date = Date()
)
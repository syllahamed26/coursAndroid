package com.mvince.compose.domain

import java.util.Date

//

data class ScoreFirebase(
    val score: Double = 0.0,
    val user: String? = null,
    val name: String? = null,
//    val date: Date = Date(),
    val date: String = "",
)


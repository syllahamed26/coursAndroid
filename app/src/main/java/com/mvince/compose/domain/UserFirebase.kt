package com.mvince.compose.domain

data class UserFirebase(
    val name: String,
    val firstname: String,
    val email: String,
    val score: Int = 0
)

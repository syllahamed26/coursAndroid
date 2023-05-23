package com.mvince.compose.ui.signin

import com.mvince.compose.domain.User

data class SingnInUiState(
    val isCorrect: Boolean = true,
    val isSingIn: Boolean = false
)
package com.mvince.compose.ui.signin

import com.mvince.compose.domain.User

data class SignUpUiState(
    val isCorrect: Boolean = true,
    val isSingUp: Boolean = false
)
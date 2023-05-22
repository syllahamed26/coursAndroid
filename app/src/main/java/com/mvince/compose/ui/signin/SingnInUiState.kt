package com.mvince.compose.ui.signin

import com.mvince.compose.domain.User

data class SingnInUiState(
    val list: List<User> = listOf(),
    val offline: Boolean = false
)
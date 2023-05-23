package com.mvince.compose.ui.leaderboard

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvince.compose.domain.ScoreFirebase
import com.mvince.compose.repository.AuthRepository
import com.mvince.compose.repository.ScoreFirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LeaderboardViewModel @Inject constructor(
        scoreRepository: ScoreFirebaseRepository,
        authRepository: AuthRepository
    )
 : ViewModel() {
    private val _scores = MutableStateFlow(emptyList<ScoreFirebase>())


    val scores: StateFlow<List<ScoreFirebase>>
        get() = _scores

    init {
        viewModelScope.launch {
            scoreRepository.getAllScore().collect({
                _scores.value = it
            })
        }
    }
}
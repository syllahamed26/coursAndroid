package com.mvince.compose.ui.finalscore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvince.compose.domain.ScoreFirebase
import com.mvince.compose.repository.AuthRepository
import com.mvince.compose.repository.ScoreFirebaseRepository
import com.mvince.compose.ui.game.GameViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FinalScoreViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val scoreFirebaseRepository: ScoreFirebaseRepository
): ViewModel(){
    private val _currentScore = MutableStateFlow<Int>(0)
    val currentScore: StateFlow<Int>
        get() = _currentScore

    private val _scores = flow {
        val scores = scoreFirebaseRepository.getLastScoreByUser(authRepository.currentUser?.uid ?: "")
        emit(scores)
        _currentScore.update {
            scores.first()[0].score
        }
        println(_currentScore.value)
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyFlow())

    val scores: StateFlow<Flow<List<ScoreFirebase>>>
        get() = _scores
}
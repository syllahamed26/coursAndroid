package com.mvince.compose.ui.finalscore

import androidx.lifecycle.ViewModel
import com.mvince.compose.ui.game.GameViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FinalScoreViewModel @Inject constructor(

): ViewModel(){
    private val _currentScore = MutableStateFlow<Int>(0)
    val currentScore: StateFlow<Int>
        get() = _currentScore

    init {
        _currentScore.update {
            0
        }
    }
}
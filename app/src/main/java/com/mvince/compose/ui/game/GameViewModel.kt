package com.mvince.compose.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvince.compose.network.model.Result
import com.mvince.compose.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val questionRepository: QuestionRepository
    ): ViewModel() {

    private val _currentQuestion = MutableStateFlow<Result?>(null)
    val currentQuestion: StateFlow<Result?>
        get() = _currentQuestion

    private val _currentScore = MutableStateFlow<Int>(0)
    val currentScore: StateFlow<Int>
        get() = _currentScore

    private val _questions = flow {
        val questions = questionRepository.getQuestions()
        emit(questions)
        _currentQuestion.update {
            questions.first()
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    var currentIndex: Int = 0;
    var currentAnswer: String? = null;

    val questions: StateFlow<List<Result>>
        get() = _questions

    fun validateAnswers(index: Int){
        //Manage score
        if(currentAnswer == currentQuestion.value?.correctAnswer){
            var points = 0;
            points = currentQuestion.value?.difficulty?.let {
                when(it){
                    "easy" -> 2
                    "medium" -> 5
                    "hard" -> 10
                    else -> 0
                }
            }!!
            _currentScore.update {
                it + points
            }
        }

        //If last question, go to result screen
        if(index == _questions.value.size - 1) {
            Timber.tag("GameViewModel").d("Score: " + _currentScore.value)
            return
        }

        //Change question
        _currentQuestion.update {
            currentIndex = index + 1
            _questions.value.get(currentIndex)
        }
    }
}
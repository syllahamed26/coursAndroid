package com.mvince.compose.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvince.compose.domain.ScoreFirebase
import com.mvince.compose.network.model.Result
import com.mvince.compose.repository.AuthRepository
import com.mvince.compose.repository.QuestionRepository
import com.mvince.compose.repository.ScoreFirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val questionRepository: QuestionRepository,
    private val scoreRepository: ScoreFirebaseRepository,
    private val authRepository: AuthRepository
    ): ViewModel() {

    private val _currentQuestion = MutableStateFlow<Result?>(null)
    val currentQuestion: StateFlow<Result?>
        get() = _currentQuestion

    private val _currentScore = MutableStateFlow<Int>(0)
    val currentScore: StateFlow<Int>
        get() = _currentScore

    private val _currentIndex = MutableStateFlow<Int>(0)
    val currentIndex : StateFlow<Int>
        get() = _currentIndex

    private val _questions = flow {
        val questions = questionRepository.getQuestions()
        emit(questions)
        _currentQuestion.update {
            questions.first()
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    var currentAnswer: String? = null;
    var finalScreen: Boolean = false;

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

            //Save score to firebase
            if(authRepository.currentUser != null){
                scoreRepository.insertScore(ScoreFirebase(_currentScore.value, authRepository.currentUser!!.uid))
            }

            //Set variable to be used to change screen to FinalScoreScreen
            finalScreen = true;
            return
        }

        //Change question
        _currentQuestion.update {
            _currentIndex.update { index + 1 }
            _questions.value.get(_currentIndex.value)
        }
    }
}
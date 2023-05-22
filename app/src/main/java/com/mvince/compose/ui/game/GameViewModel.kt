package com.mvince.compose.ui.game

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

    private val _falseAnswer = MutableStateFlow<Boolean>(false)
    val falseAnswer: StateFlow<Boolean>
        get() = _falseAnswer

    private val _questions = flow {
        val questions = questionRepository.getQuestions()
        emit(questions)
        _currentQuestion.update {
            questions.first()
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    var currentIndex: Int = 0;
    var currentAnswer: String? = null;
    var finalScreen: Boolean = false;

    val questions: StateFlow<List<Any>>
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
            _falseAnswer.update {
                points == 0
            }
        }

        //delay to show color change
        //Thread.sleep(2000)

        //If last question, go to result screen
        if(index == _questions.value.size - 1) {
            Timber.tag("GameViewModel").d("Score: " + _currentScore.value)

            //Set variable to be used to change screen to FinalScoreScreen
            finalScreen = true;
            return
        }

        //Change question
        _currentQuestion.update {
            currentIndex = index + 1
            _questions.value.get(currentIndex)
        }
    }
}
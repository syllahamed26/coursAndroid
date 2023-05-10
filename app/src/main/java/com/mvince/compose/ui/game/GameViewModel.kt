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
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val questionRepository: QuestionRepository
    ): ViewModel() {

    private val _currentQuestion = MutableStateFlow<Result?>(null)
    val currentQuestion: StateFlow<Result?>
        get() = _currentQuestion

    private val _questions = flow {
        val questions = questionRepository.getQuestions()
        emit(questions)
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val questions: StateFlow<List<Result>>
        get() = _questions

    fun validateAnswers(index: Int){
        //Manage score

        //Change question
        _currentQuestion.update {
            _questions.value.get(index)
        }
    }
}
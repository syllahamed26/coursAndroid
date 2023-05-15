package com.mvince.compose.ui.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(){
    val viewModel = hiltViewModel<GameViewModel>()

    val questions =  viewModel.questions.collectAsState().value
    val currentQuestion = viewModel.currentQuestion.collectAsState().value
    val currentIndex = viewModel.currentIndex;

    Scaffold() {
        Column(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.Center
        ) {
//            questions.forEach {
//                Text(text = it.question)
//            }
            if (currentQuestion != null) {
                Text(text = currentQuestion.question)
                val answers = currentQuestion.incorrectAnswers + currentQuestion.correctAnswer
                answers.shuffled().forEachIndexed { index, answer ->
                    Row {
                        RadioButton(
                            selected = viewModel.currentAnswer == answer,
                            onClick = {
                                if(currentIndex + 1 <= questions.size){
                                    viewModel.currentAnswer = answer
                                    viewModel.validateAnswers(currentIndex)
                                }
                            }
                        )
                        ClickableText(
                            text = AnnotatedString(answer),
                            onClick = {
                                if(currentIndex + 1 <= questions.size){
                                    viewModel.currentAnswer = answer
                                    viewModel.validateAnswers(currentIndex)
                                }
                            },
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            }
        }
    }
}
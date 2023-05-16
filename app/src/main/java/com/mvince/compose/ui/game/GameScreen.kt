package com.mvince.compose.ui.game

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mvince.compose.ui.Route

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(navController: NavController){
    val viewModel = hiltViewModel<GameViewModel>()

    val questions =  viewModel.questions.collectAsState().value
    val currentQuestion = viewModel.currentQuestion.collectAsState().value
    val currentIndex = viewModel.currentIndex;

    Scaffold() {
        Column(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.Center
        ) {
//
            if (currentQuestion != null) {
                Text(
                    text = currentQuestion.question,
                    modifier = Modifier.padding(25.dp),
                    style = MaterialTheme.typography.titleLarge
                )
                val answers = currentQuestion.incorrectAnswers + currentQuestion.correctAnswer
                answers.shuffled().forEachIndexed { index, answer ->
                    //Display each answer as button centered in a row
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Button(
                            onClick = {
                                viewModel.currentAnswer = answer

                                //send answer to viewmodel
                                viewModel.validateAnswers(currentIndex)
                                if (viewModel.finalScreen) {
                                    navController.navigate(Route.FINAL_SCORE)
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
//                            colors = ButtonDefaults.buttonColors(
//                                containerColor = if (viewModel.falseAnswer.value) Color.Red else Color.Blue
//                            )
                        ) {
                            Text(
                                text = answer,
                                //modifier = Modifier.padding(25.dp),
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }
            }
        }
    }
}
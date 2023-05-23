package com.mvince.compose.ui.game

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mvince.compose.ui.Route
import com.mvince.compose.ui.home.BottomRoute

@SuppressLint("StateFlowValueCalledInComposition", "SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(navController: NavController){
    val viewModel = hiltViewModel<GameViewModel>()

    val questions =  viewModel.questions.collectAsState().value
    val currentQuestion = viewModel.currentQuestion.collectAsState().value
    val currentIndex = viewModel.currentIndex.collectAsState().value;
    val currentScore = viewModel.currentScore.collectAsState().value
    val finalQuestion = viewModel.finalScreen.collectAsState().value

    LaunchedEffect(key1 = finalQuestion, block = {
        if(finalQuestion){
            //TODO: Navigate to final score screen but not working
            navController.navigate(BottomRoute.FINAL_SCORE)
        }
    })
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                //modifier = Modifier.padding(it),
                verticalArrangement = Arrangement.Center
            ) {
                if (currentQuestion != null) {

                    Text(
                        text = "Question ${currentIndex + 1} / ${questions.size}",
                        modifier = Modifier.padding(25.dp),
                        style = MaterialTheme.typography.titleMedium
                    );
                    Text(
                        text = currentQuestion.question.toString(),
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
                                    viewModel.setCurrentAnswer(answer.toString())
                                    viewModel.validateAnswers(currentIndex)
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                            ) {
                                Text(
                                    text = answer.toString(),
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        }
                    }
                    Text(
                        text = "Score: $currentScore",
                        modifier = Modifier.padding(25.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                }

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                AnimatedVisibility(visible = questions.isEmpty() && currentScore == 0 ) {
                    CircularProgressIndicator(modifier = Modifier.then(Modifier.size(100.dp)))
                }
            }
        }

    }
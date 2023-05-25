package com.mvince.compose.ui.game

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mvince.compose.ui.Route
import com.mvince.compose.ui.finalscore.FinalScoreScreen
import com.mvince.compose.ui.home.BottomRoute
import com.mvince.compose.ui.theme.Green700

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
    val isAlreadyPLaying = viewModel.isAlreadyPlaying.collectAsState().value

    LaunchedEffect(key1 = finalQuestion, block = {
        if(finalQuestion){
            //TODO: Navigate to final score screen but not working
            navController.navigate(BottomRoute.FINAL_SCORE)
        }
    })
        Box(modifier = Modifier.fillMaxSize()) {
            if(!isAlreadyPLaying) {
                Column(
                    //modifier = Modifier.padding(it),
                    verticalArrangement = Arrangement.Center
                ) {
                    if (currentQuestion != null) {

                        ElevatedCard(
                            modifier = Modifier
                                .padding(top = 100.dp, start = 20.dp, end = 20.dp, bottom = 40.dp)
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    text = "Question ${currentIndex + 1} / ${questions.size}",
                                    modifier = Modifier.padding(25.dp),
                                    style = MaterialTheme.typography.titleMedium
                                );

                                Text(
                                    fontWeight = FontWeight.Bold,
                                    text = "Score $currentScore",
                                    modifier = Modifier.padding(25.dp),
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.Black
                                )
                            }
                            Text(
                                color = Color.Black,
                                text = currentQuestion.question.toString(),
                                modifier = Modifier.padding(25.dp),
                                style = MaterialTheme.typography.titleLarge,
                                textAlign = TextAlign.Justify
                            )
                        }
                        val answers =
                            currentQuestion.incorrectAnswers + currentQuestion.correctAnswer
                        answers.shuffled().forEachIndexed { index, answer ->
                            //Display each answer as button centered in a row
                            Row(
                                modifier = Modifier.padding(horizontal = 20.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Button(
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                    onClick = {
                                        viewModel.setCurrentAnswer(answer.toString())
                                        viewModel.validateAnswers(currentIndex)
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(5.dp),
                                ) {
                                    Text(
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black,
                                        text = answer.toString(),
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                }
                            }

                        }
                    }
                }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AnimatedVisibility(visible = questions.isEmpty() && currentScore == 0.0) {
                        CircularProgressIndicator(
                            modifier = Modifier.then(Modifier.size(100.dp)),
                            color = Color.White
                        )
                    }
                }
            }else {
                FinalScoreScreen(navController = navController, textDisplay = "You already played today")
            }
        }
}
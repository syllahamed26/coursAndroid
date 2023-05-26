package com.mvince.compose.ui.finalscore

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mvince.compose.R
import com.mvince.compose.ui.Route
import com.mvince.compose.ui.home.BottomRoute
import com.mvince.compose.ui.theme.*

@SuppressLint("StateFlowValueCalledInComposition", "UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinalScoreScreen(navController: NavController, textDisplay: String? = null) {

    val viewModel = hiltViewModel<FinalScoreViewModel>()

    val currentScore = viewModel.currentScore.collectAsState()

    val scores = viewModel.scores.collectAsState().value


    ElevatedCard(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 50.dp, vertical = 200.dp), shape = RoundedCornerShape(30.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = textDisplay ?: "End of the game",
                fontSize = 30.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Your score \n",
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 25.sp,
                    color = GreyLight,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${currentScore.value}",
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 50.sp,
                    color = Green700,
                    fontWeight = FontWeight.Bold
                )
            }

            Button(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                shape = RoundedCornerShape(30.dp),
                onClick = {
                    navController.navigate(BottomRoute.PROFILE)
                },
            ) {
                Text(
                    text = "Go to the home screen",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

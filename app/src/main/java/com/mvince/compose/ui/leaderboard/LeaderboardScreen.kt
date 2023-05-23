package com.mvince.compose.ui.leaderboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LeaderboardScreen() {
    val viewModel = hiltViewModel<LeaderboardViewModel>()

    val scores = viewModel.scores.collectAsState().value

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            //modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.Center
        ) {
            scores.forEachIndexed { index, score ->
                Text(text = "${index + 1} - ${score.user} : ${score.score}")
            }
        }
    }
}
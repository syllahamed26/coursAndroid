package com.mvince.compose.ui.finalscore

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinalScoreScreen() {

    val viewModel = hiltViewModel<FinalScoreViewModel>()

    Scaffold {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Text(text = "Fin de la partie")
            Text(text = "Votre score est de ${viewModel.currentScore.value}")
        }
    }
}
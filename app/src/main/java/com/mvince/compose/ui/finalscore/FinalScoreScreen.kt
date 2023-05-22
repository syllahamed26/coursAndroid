package com.mvince.compose.ui.finalscore

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mvince.compose.ui.Route

@SuppressLint("StateFlowValueCalledInComposition", "UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinalScoreScreen(navController: NavController) {

    val viewModel = hiltViewModel<FinalScoreViewModel>()

    val currentScore = viewModel.currentScore.collectAsState()

    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Fin de la partie")
            Text(modifier = Modifier.padding(20.dp), text = "Votre score est de ${currentScore.value}",style = MaterialTheme.typography.titleMedium
            )
            Button(onClick = {navController.navigate(Route.HOME)}) {
                Text(text = "Retour à l'accueil")
            }
        }
//        Divider(
//            modifier = Modifier.padding(25.dp)
//        )
    }
}
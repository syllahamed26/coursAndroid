package com.mvince.compose.ui.home

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mvince.compose.ui.Route
import com.mvince.compose.ui.components.NavigationBar
import com.mvince.compose.ui.finalscore.FinalScoreScreen
import com.mvince.compose.ui.game.GameScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    Scaffold(bottomBar = { NavigationBar(navController = navController) }) {
        NavHost(
            navController = navController,
            startDestination = BottomRoute.HOME
        ) {
            composable(BottomRoute.HOME) {
                HomeScreen()
            }
            composable(BottomRoute.GAME) {
                GameScreen(navController = navController)
            }
            composable(BottomRoute.FINAL_SCORE) {
                FinalScoreScreen()
            }
        }
    }

}

object BottomRoute {
    const val GAME = "game"
    const val HOME = "home"
    const val FINAL_SCORE = "finalscore"
}
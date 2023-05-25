package com.mvince.compose.ui.home

import android.annotation.SuppressLint
import androidx.compose.material3.CircularProgressIndicator
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
import com.mvince.compose.ui.leaderboard.LeaderboardScreen
import com.mvince.compose.ui.profile.ProfileScreen
import com.mvince.compose.ui.signin.SignInScreen

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
                FinalScoreScreen(navController = navController)
            }
            composable(BottomRoute.LEADERBOARD) {
                LeaderboardScreen()
            }
            composable(BottomRoute.PROFILE) {
                ProfileScreen(navController = navController)
            }
            composable(BottomRoute.SIGN_IN) {
                SignInScreen(navController = navController)
            }
        }
    }

}

object BottomRoute {
    const val GAME = "game"
    const val HOME = "home"
    const val FINAL_SCORE = "finalscore"
    const val LEADERBOARD = "leaderboard"
    const val PROFILE = "profile"
    const val SIGN_IN = "signin"
}
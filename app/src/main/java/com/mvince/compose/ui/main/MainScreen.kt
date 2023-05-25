package com.mvince.compose.ui.home

import android.annotation.SuppressLint
import androidx.annotation.Px
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.annotations.concurrent.Background
import com.mvince.compose.ui.Route
import com.mvince.compose.ui.components.IconMenu
import com.mvince.compose.ui.components.NavigationBar
import com.mvince.compose.ui.finalscore.FinalScoreScreen
import com.mvince.compose.ui.game.GameScreen
import com.mvince.compose.ui.leaderboard.LeaderboardScreen
import com.mvince.compose.ui.profile.ProfileScreen
import com.mvince.compose.ui.signin.SignInScreen
import com.mvince.compose.ui.theme.*
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    val navController = rememberNavController()

    Scaffold(bottomBar = { NavigationBar(navController = navController) }, topBar = { IconMenu(
        id = "",
        firstName = "A",
        lastName = "A",
    )},
    ) {
        Box(modifier = Modifier
            .background(PrimaryGradiant)
            .fillMaxSize()){
            NavHost(
                navController = navController,
                startDestination = BottomRoute.HOME
            ) {
                composable(BottomRoute.HOME) {
                    HomeScreen(navController = navController)
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
}

object BottomRoute {
    const val GAME = "game"
    const val HOME = "home"
    const val FINAL_SCORE = "finalscore"
    const val LEADERBOARD = "leaderboard"
    const val PROFILE = "profile"
    const val SIGN_IN = "signin"
}
package com.mvince.compose.ui.home

import android.annotation.SuppressLint
import android.app.MediaRouteButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mvince.compose.ui.Route

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun HomeScreen(navController: NavController) {

    ElevatedCard(
        modifier = Modifier
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
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                shape = RoundedCornerShape(30.dp),
                onClick = {
                    navController.navigate(BottomRoute.GAME)
                },
            ) {
                Text(
                    text = "Game",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                shape = RoundedCornerShape(30.dp),
                onClick = {
                    navController.navigate(BottomRoute.LEADERBOARD)
                },
            ) {
                Text(
                    text = "Leaderboard",
                    style = MaterialTheme.typography.titleMedium
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
                    text = "Profile",
                    style = MaterialTheme.typography.titleMedium
                )

            }
        }
    }
}

package com.mvince.compose.ui.home

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mvince.compose.ui.ComposeApp
import com.mvince.compose.ui.components.NavigationBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar(navController = navController)
        }
    ) {

    }

}
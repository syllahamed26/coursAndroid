package com.mvince.compose.ui.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mvince.compose.ui.components.IconUser

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "ResourceType")
@Composable
fun ProfileScreen() {
    val viewModel = hiltViewModel<ProfileViewModel>()

    val user = viewModel.user.collectAsState().value

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
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Profile",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )

            IconUser(id = "id", firstName = "${user?.displayName}",
                lastName = "${user?.firstname}"
                , size = 100.dp, fontSize = 50.sp)

            Text(text = user?.displayName + " " + user?.firstname,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold)

        }
    }
}
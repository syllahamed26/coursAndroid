package com.mvince.compose.ui.leaderboard


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mvince.compose.R
import com.mvince.compose.ui.theme.GreyLight
import com.mvince.compose.ui.theme.SoftWhite

//import com.mvince.compose.ui.leaderboard.components

@Composable
fun LeaderboardScreen() {
    val viewModel = hiltViewModel<LeaderboardViewModel>()

    val scores = viewModel.scores.collectAsState().value

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            //modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.Center
        ) {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 20.dp),
                shape = RoundedCornerShape(30.dp)
            ) {
                Column(modifier = Modifier.padding(horizontal = 40.dp, vertical = 20.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Icon(
                            modifier = Modifier.height(50.dp),
                            painter = painterResource(id = R.drawable.ic_trophy),
                            contentDescription = ""
                        )

                        Spacer(Modifier.width(20.dp))

                        Text(
                            text = "1",
                            style = MaterialTheme.typography.titleMedium,
                            fontSize = 60.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )

                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Antoine",
                                style = MaterialTheme.typography.titleMedium,
                                fontSize = 20.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Andre",
                                style = MaterialTheme.typography.titleMedium,
                                fontSize = 20.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )

                        }

                        Divider(
                            color = Color.LightGray,
                            modifier = Modifier
                                .fillMaxHeight()  //fill the max height
                                .width(2.dp)
                                .padding(vertical = 40.dp)
                        )

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Score",
                                style = MaterialTheme.typography.titleMedium,
                                fontSize = 35.sp,
                                color = GreyLight,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "30",
                                style = MaterialTheme.typography.titleMedium,
                                fontSize = 25.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            scores.forEachIndexed { index, score ->
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp, vertical = 10.dp),
                    shape = CutCornerShape(10.dp),
                    colors = CardDefaults.cardColors(
                         containerColor = SoftWhite,
                     ),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(modifier = Modifier
                            .width(50.dp)) {
                            Row (verticalAlignment = Alignment.CenterVertically){
                                Text(text = "${index + 1} ")

                                Spacer(modifier = Modifier.width(20.dp))

                                Divider(
                                    color = Color.LightGray,
                                    modifier = Modifier
                                        .fillMaxHeight()  //fill the max height
                                        .width(2.dp)
                                        .padding(vertical = 15.dp)
                                )
                            }
                        }
                        Text(text = "${score.name}")
                        Text(text = "${score.score} ")
                    }
                }
            }
        }
    }
}
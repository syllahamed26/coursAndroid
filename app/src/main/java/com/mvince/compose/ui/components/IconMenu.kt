package com.mvince.compose.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mvince.compose.R
import com.mvince.compose.ui.theme.PrimaryGradiant
import com.mvince.compose.ui.theme.toHslColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconMenu(
    id: String,
    firstName: String,
    lastName: String,
    modifier: Modifier = Modifier,
    size: Dp = 40.dp,
    textStyle: TextStyle = MaterialTheme.typography.headlineSmall,
) {

    var expanded by remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = PrimaryGradiant),
        title = {
            Text(
                stringResource(id = R.string.app_name),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
        },
        actions = {
            Box(){
                IconButton(onClick = { expanded = true }) {
                    Box(modifier.size(size), contentAlignment = Alignment.Center) {
                        val color = remember(id, firstName, lastName) {
                            val name = listOf(firstName, lastName)
                                .joinToString(separator = "")
                                .uppercase()
                            Color("$id / $name".toHslColor())
                        }
                        val initials = (firstName.take(1) + lastName.take(1)).uppercase()
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            drawCircle(SolidColor(color))
                        }
                        Text(
                            text = initials,
                            style = textStyle,
                            color = Color.White,
                            fontSize = 15.sp
                        )
                    }
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Déconnection") },
                        onClick = { /* Handle edit! */ },
                        leadingIcon = {
                            Icon(
                                painterResource(id = R.drawable.ic_login),
                                contentDescription = null
                            )
                        })
                }
            }
        }
    )
}

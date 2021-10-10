package ru.vtb.moretech.stats

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ru.vtb.moretech.career.CareerTopBar


@Composable
fun StatsScreen(
    navController: NavHostController,
    viewModel: StatsViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = { CareerTopBar(navController, "Профиль") }
    ) {
        Column() {
            val userStatsState by viewModel.userStats.collectAsState()
            Text(
                text = "Здравствуйте, ${UserStatsController.user?.name}",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Card(
                    modifier = Modifier
                        .widthIn(max = 200.dp)
                        .border(5.dp, Color(0xFF626F84)),
                    backgroundColor = Color(0xFF172030)
                ) {
                    Box(Modifier.padding(15.dp)) {
                        Column(
                            modifier = Modifier
                                .padding(end = 30.dp)
                                .align(Alignment.TopStart)
                        ) {
                            Text(
                                text = "Решено уроков",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = if (userStatsState == null) "..." else userStatsState!!.lessonsDone.toString(),
                                color = Color.White,
                                modifier = Modifier.widthIn(max = 1500.dp)
                            )
                        }

                    }
                }
            }
        }
    }
}
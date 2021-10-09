package ru.vtb.moretech

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import ru.vtb.moretech.screen.AnswerButton
import ru.vtb.moretech.screen.SubHeader
import ru.vtb.moretech.screen.dynamicStringRes

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Theory(    navController: NavHostController) {

    var indicatorState by rememberSaveable {
        mutableStateOf(0.2f)
    }
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Scaffold(topBar = {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(text = "${(indicatorState*10/2).toInt()}/5")
            Spacer(modifier = Modifier.width(20.dp))
            LinearProgressIndicator(
                progress = indicatorState,
                color = Color(0xFF3A83F1),
                modifier = Modifier
                    .height(20.dp)
                    .clip(RoundedCornerShape(8.dp)),
                backgroundColor = Color.White
            )
        }

    }) {
        HorizontalPager(count = 2, state = pagerState) { page ->
            if (page == 0) {
                Column(
                    Modifier.verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Text(
                        text = dynamicStringRes(prefix = "theory", i = page), color = Color.Black,
                        modifier = Modifier

                            .padding(start = 20.dp, end = 20.dp)

                            .clip(RoundedCornerShape(25.dp))
                            .background(Color.White)
                            .padding(25.dp)
                    )
                    AnswerButton(text = "Далее", color = Color(0xFF3A83F1)) {
                        scope.launch {
                            pagerState.scrollToPage(1)
                            indicatorState = 0.4f
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            } else {

                Column(
                    Modifier.verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    SubHeader(icon = R.drawable.dialog_1) {
                        Text(
                            text = dynamicStringRes(prefix = "theory", i = page), color = Color.Black,
                        )
                    }
                    Spacer(modifier = Modifier.height(50.dp))
                    AnswerButton(text = "Буду хранить деньги на карте", color = Color(0xFF3A83F1)) {
                        navController.navigate("Respect/2")
                    }
                    AnswerButton(text = "Буду хранить деньги в домашнем сейфе", color = Color(0xFF3A83F1)) {
                        navController.navigate("Respect/1")
                    }
                    AnswerButton(text = "Положу деньги на вклад с процентной ставкой", color = Color(0xFF3A83F1)) {
                        navController.navigate("Respect/2")
                    }
                    AnswerButton(text = "Все деньги в портфель", color = Color(0xFF3A83F1)) {
                        navController.navigate("Respect/0")
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }

        }
    }

}
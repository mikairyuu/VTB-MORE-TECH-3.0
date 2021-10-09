package ru.vtb.moretech.screen

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.vtb.moretech.R
import ru.vtb.moretech.day.DayTopBar
import ru.vtb.moretech.ui.theme.Message
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun DayScreen(
    navController: NavHostController,
    viewModel: DayViewModel = hiltViewModel()
) {

    val dayState by viewModel.dayState.collectAsState()
    val headerState by viewModel.headerState.collectAsState()
    val answer by viewModel.answer.collectAsState()

    val isNeededToNavigate by viewModel.isNeededToNavigate.collectAsState()

    if (isNeededToNavigate) {
        navController.navigate("CareerPlan")
    }

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val dialogAvatar = listOf(
        idDrawableRes(prefix = "dialog", i = 1),
        idDrawableRes(prefix = "dialog", i = 2),
        idDrawableRes(prefix = "dialog", i = 3)
    )

    Scaffold(topBar = {
        DayTopBar(text = stringResource(R.string.day_title_0))
    }) {

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier, state = listState) {
                item {
                    Header(R.drawable.sticker_1_copy) {
                        Text(
                            text = dynamicStringRes("day_header", dayState, headerState),
                            color = Color.Black
                        )
                    }
                }
                if (answer > 2) {
                    itemsIndexed(viewModel.messages) { index, item ->
                        SubHeader(dialogAvatar.random()) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = item,
                                    color = Color.Black
                                )
                            }
                        }
                        if (index == viewModel.messages.size - 1)
                            Spacer(modifier = Modifier.height(300.dp))
                        else
                            Spacer(modifier = Modifier.height(30.dp))
                    }
                    coroutineScope.launch {
//                        if (viewModel.messages.size > 1) {
                        // listState.scrollToItem(viewModel.messages.size-1)
                        listState.animateScrollToItem(viewModel.messages.size - 1)
                        Log.d("TESTING", "SCROLL ${viewModel.messages.size - 1}")
//                        }

                    }
                }


            }
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp)
            ) {
                val buttons = dynamicStringArrayRes(prefix = "answer", i = dayState, j = answer)
                for ((index, temp) in buttons.withIndex()) {
                    AnswerButton(
                        temp,
                        if (index != buttons.size - 1) Color(0xFF3A83F1) else Color(0xFF225094)
                    ) {
                        viewModel.onClick(index)
                    }
                }

            }

        }


    }

}


@Composable
fun Header(@DrawableRes icon: Int, content: @Composable () -> Unit) {

    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 10.dp, top = 80.dp)
                .size(190.dp, 190.dp)
        )

        MessageItem(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 20.dp, top = 10.dp)
        ) {
            content()
        }
    }

}

@Composable
fun MessageItem(modifier: Modifier, content: @Composable () -> Unit) {
    Column(modifier) {
        Box(
            modifier = Modifier
                .clip(Message())
                .background(Color.White)
                .padding(bottom = 25.dp, top = 10.dp, start = 15.dp, end = 10.dp)
                .wrapContentSize()
        ) {
            Column(
                modifier = Modifier.width(180.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                content()
            }

        }

    }
}

@Composable
fun AnswerButton(text: String, color: Color, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 50.dp)
            .padding(start = 20.dp, end = 20.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color
        )
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )

    }
}

@Composable
fun SubHeader(@DrawableRes icon: Int, content: @Composable () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
    ) {

        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(start = 15.dp, top = 15.dp)
                .clip(RoundedCornerShape(18.dp))
                .fillMaxWidth()
                .background(Color.White)
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            content()
        }
        Card(
            modifier = Modifier
                .align(Alignment.TopStart)
                .border(
                    border = BorderStroke(2.dp, Color(0xFF3A83F1)),
                    shape = CircleShape
                ),
            shape = CircleShape,
            backgroundColor = Color.White
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp, 48.dp)
                    .padding(5.dp)
            )
        }
    }
}

@Composable
fun dynamicStringRes(prefix: String, i: Int, j: Int): String {
    val content = LocalContext.current

    val id = content.resources.getIdentifier("${prefix}_${i}_${j}", "string", "ru.vtb.moretech")
    return stringResource(id)
}

@Composable
fun dynamicStringArrayRes(prefix: String, i: Int, j: Int): Array<String> {

    val content = LocalContext.current

    val id = content.resources.getIdentifier("${prefix}_${i}_${j}", "array", "ru.vtb.moretech")
    return stringArrayResource(id)
}

@Composable
fun idDrawableRes(prefix: String, i: Int): Int {
    val content = LocalContext.current

    return content.resources.getIdentifier("${prefix}_${i}", "drawable", "ru.vtb.moretech")
}
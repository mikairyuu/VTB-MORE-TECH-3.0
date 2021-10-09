package ru.vtb.moretech.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vtb.moretech.R
import ru.vtb.moretech.day.DayTopBar
import ru.vtb.moretech.ui.theme.Message
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DayScreen(viewModel: DayViewModel = viewModel()) {


    Scaffold(topBar = {
        DayTopBar(text = stringResource(R.string.day_title_0))
    }) {

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier) {
                item {
                    Header(R.drawable.sticker_1_copy) {
                        Text(
                            text = dynamicRes(),
                            color = Color.Black
                        )
                    }
                }
                item {
                    SubHeader(R.drawable.sticker_9) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = stringResource(R.string.message_title_0_2),
                                color = Color.Black
                            )
                        }
                    }
                }

            }
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp)
            ) {
                AnswerButton(stringResource(R.string.answer_0_0_1), Color(0xFF3A83F1)) {
                    viewModel.onClick(0)
                }
                AnswerButton(stringResource(R.string.answer_0_0_2), Color(0xFF225094)) {
                    viewModel.onClick(1)
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
fun dynamicRes(): String {
    val content = LocalContext.current

    val i = 0
    val j = 2
    val id = content.resources.getIdentifier("day_header_${i}_${j}", "string", "ru.vtb.moretech")
    return stringResource(id)

}
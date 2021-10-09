package ru.vtb.moretech

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import kotlinx.coroutines.launch
import ru.vtb.moretech.screen.AnswerButton
import ru.vtb.moretech.screen.dynamicStringRes
import ru.vtb.moretech.screen.idDrawableRes

@OptIn(ExperimentalUnitApi::class)
@Composable
fun Respect(respectId: Int) {

    Scaffold() {
        Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = dynamicStringRes(prefix = "respect", i = respectId),
            fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
            fontSize = 22.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Image(
                painterResource(idDrawableRes(prefix = "sticker_respect", i = respectId)),
                null,
                modifier = Modifier.height(200.dp))
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = dynamicStringRes(prefix = "respect_text", i = respectId), color = Color.Black,
                modifier = Modifier

                    .padding(start = 20.dp, end = 20.dp)

                    .clip(RoundedCornerShape(25.dp))
                    .background(Color.White)
                    .padding(25.dp),
                letterSpacing = TextUnit(1.8f, TextUnitType.Sp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            AnswerButton(text = dynamicStringRes(prefix = "respect_button", i = respectId), color = Color(0xFF3A83F1)) {

                }
            }

    }

}
package ru.vtb.moretech.career

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vtb.moretech.R

@Composable
fun CareerTopBar() {
    TopAppBar(
        contentPadding = PaddingValues(0.dp),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.height(50.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.padding(top = 10.dp)
                    .clip(
                        RoundedCornerShape(0.dp, 8.dp, 8.dp, 0.dp)
                    )
                    .background(Color(0xFF3A83F1))
                    .padding(10.dp)
            ) {
                Text(
                    text = "Карьерный супер-план",
                    fontWeight = FontWeight.Bold,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.ic_union),
                contentDescription = null,
                tint = Color(0xFFF2C94C),
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(
                text = stringResource(R.string.money_format, 100),
                modifier = Modifier.padding(end = 10.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}
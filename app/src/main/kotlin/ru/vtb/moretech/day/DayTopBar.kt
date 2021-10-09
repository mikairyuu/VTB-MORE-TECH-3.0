package ru.vtb.moretech.day

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DayTopBar(text: String) {
    TopAppBar(
        contentPadding = PaddingValues(0.dp),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.background
    ) {
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(0.dp, 8.dp, 8.dp, 0.dp)
                )
                .background(Color(0xFF3A83F1))
                .padding(10.dp)
        ) {
            Text(text = text, fontWeight = FontWeight.Bold)
        }
    }
}
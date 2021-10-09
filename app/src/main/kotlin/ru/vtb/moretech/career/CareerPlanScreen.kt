package ru.vtb.moretech.career

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ru.vtb.moretech.R

@Composable
fun CareerPlanScreen(navController: NavHostController) {

    val list = stringArrayResource(R.array.career_plan)

    Scaffold(
        topBar = {
           CareerTopBar()
        }
    ) {
        Divider(
            color = Color.White,
            modifier = Modifier
                .padding(start = 30.dp)
                .fillMaxHeight()
                .width(10.dp)

        )
        LazyColumn(
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(list) { index, item ->
                if (index == 0)
                    Spacer(modifier = Modifier.height(40.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(if (index == 0) Color(0xFF3A83F1) else Color.White)

                    )
                    Column(modifier = Modifier    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { if (index == 0) {
                        navController.navigate("Theory")
                    }
                    }) {
                        Card(
                            modifier = Modifier.widthIn(max = 200.dp),
                            shape = RoundedCornerShape(18.dp),
                            backgroundColor = Color.White
                        ) {
                            Box(Modifier.padding(15.dp)) {
                                Column(modifier = Modifier
                                    .padding(end = 30.dp)
                                    .align(Alignment.TopStart)) {
                                    Text(text ="День ${index+1}", color = Color.Black, fontWeight = FontWeight.Bold)
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Text(text = item, color = Color.Black, modifier = Modifier.widthIn(max = 1500.dp))
                                }
                                Icon(
                                    painter = painterResource(id = if (index == 0) R.drawable.ic_union else R.drawable.ic_castle),
                                    contentDescription = null,
                                    tint = if (index == 0) Color(0xFFF2C94C) else Color(0xFF626F84),
                                    modifier = Modifier.align(Alignment.BottomEnd)
                                )

                            }

                        }
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))



            }
        }
    }

}
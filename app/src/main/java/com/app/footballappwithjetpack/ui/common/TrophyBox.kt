package com.app.footballappwithjetpack.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TrophyBox(
    modifier: Modifier = Modifier,
    name: String,
    image: Int
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = modifier.size(100.dp),
            painter = painterResource(id = image),
            contentDescription = ""
        )
        Text(
            modifier = Modifier.padding(2.dp),
            text = name,
            fontSize = 15.sp
        )
    }
}
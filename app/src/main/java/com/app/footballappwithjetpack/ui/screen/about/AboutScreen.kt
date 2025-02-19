package com.app.footballappwithjetpack.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.footballappwithjetpack.R
import com.app.footballappwithjetpack.ui.common.TopNavBar
import com.app.footballappwithjetpack.ui.theme.MainColor

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues
){
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPadding)
            .verticalScroll(scrollState)
    ) {
        TopNavBar(name = "About")
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = modifier.size(80.dp))
            Image(
                modifier = modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .border(width = 2.dp, color = MainColor, shape = CircleShape),
                painter = painterResource(id = R.drawable.myfotoprofile),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = modifier.size(20.dp))
            Text(
                text = "Ibra Sahrian Alsa",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = modifier.padding(bottom = 80.dp),
                text = "ibrarian9@gmail.com",
                fontSize = 15.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

        }
    }
}


package com.app.footballappwithjetpack.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.footballappwithjetpack.model.Club
import com.app.footballappwithjetpack.ui.theme.MainColor

@Composable
fun ClubBox(
    club: Club,
    modifier: Modifier = Modifier,
    navigateToDetail: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(MainColor)
            .clickable {
                navigateToDetail()
            }
    ) {
        Image(
            modifier = modifier.size(120.dp)
                .padding(5.dp)
                .align(alignment = Alignment.CenterVertically),
            painter = painterResource(id = club.poto),
            contentDescription = club.name
        )
        Column(
            modifier = modifier
                .weight(1f)
                .height(120.dp)
                .padding(5.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = modifier.fillMaxWidth(),
                text = club.name,
                color = Color.White,
                textAlign = TextAlign.Center,
                maxLines = 1,
                fontSize = 18.sp,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = modifier.fillMaxWidth()
                    .padding(end = 10.dp, top = 5.dp),
                text = club.desc,
                color = Color.White,
                textAlign = TextAlign.Justify,
                maxLines = 3,
                fontSize = 12.sp,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
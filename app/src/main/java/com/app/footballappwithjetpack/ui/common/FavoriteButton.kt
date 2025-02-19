package com.app.footballappwithjetpack.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.footballappwithjetpack.model.Club
import com.app.footballappwithjetpack.ui.screen.detail.DetailViewModel
import com.app.footballappwithjetpack.ui.theme.MainColor

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    club: Club,
    detailViewModel: DetailViewModel
){

    val isFav by detailViewModel.isFav.observeAsState(initial = false)

    LaunchedEffect(key1 = club) {
        detailViewModel.checkFavorite(club)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MainColor)
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            IconButton(
                modifier = Modifier.size(30.dp),
                onClick = {
                    if (isFav)
                        detailViewModel.removeFavorite(club)
                    else
                        detailViewModel.addFavorite(club)
                }) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = if (isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "",
                    tint = Color.White
                )
            }
            Text(
                text = "Favorite",
                color = Color.White,
                fontSize = 12.sp
            )
        }
    }
}
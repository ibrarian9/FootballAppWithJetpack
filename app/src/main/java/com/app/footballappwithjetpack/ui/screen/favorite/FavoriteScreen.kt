package com.app.footballappwithjetpack.ui.screen.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.app.footballappwithjetpack.ui.common.ClubBox
import com.app.footballappwithjetpack.ui.common.TopNavBar
import org.koin.androidx.compose.getViewModel

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    innerPaddingValues: PaddingValues,
    navigateToDetail: (Int) -> Unit,
    viewModel: FavoriteViewModel = getViewModel()
) {
    val clubs by viewModel.favoriteClubs.observeAsState(emptyList())

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPaddingValues)
            .background(color = Color.White)
    ) {
        TopNavBar(name = "Favorite Club")

        // display filter list
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            items(clubs) { club ->
                ClubBox(
                    club = club,
                    navigateToDetail = {
                        navigateToDetail(club.id)
                    }
                )
            }
        }
    }
}
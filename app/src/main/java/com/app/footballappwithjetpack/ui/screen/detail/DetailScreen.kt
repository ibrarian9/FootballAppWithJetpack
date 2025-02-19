package com.app.footballappwithjetpack.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.footballappwithjetpack.R
import com.app.footballappwithjetpack.ui.common.FavoriteButton
import com.app.footballappwithjetpack.ui.common.TopNavBar
import com.app.footballappwithjetpack.ui.common.TrophyBox
import org.koin.androidx.compose.getViewModel

@Composable
fun DetailScreen(
    clubId: Int,
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues,
    viewModel: DetailViewModel = getViewModel()
) {
    val clubs by viewModel.clubs.observeAsState(emptyList())

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        clubs.getOrNull(clubId)?.let { club ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                // Use LazyColumn to support scrolling in both Portrait & Landscape mode
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 80.dp), // Reserve space for FavoriteButton
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    item {
                        TopNavBar(name = club.name)
                    }
                    item {
                        // Image with Description
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(10.dp)
                                    .sizeIn(
                                        minWidth = 150.dp,
                                        minHeight = 250.dp,
                                        maxHeight = 250.dp
                                    ),
                                contentScale = ContentScale.Fit,
                                painter = painterResource(id = club.poto),
                                contentDescription = ""
                            )
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(270.dp)
                                    .padding(10.dp)
                            ) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = club.name,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(end = 10.dp, top = 5.dp),
                                    text = club.desc,
                                    color = Color.Black,
                                    textAlign = TextAlign.Justify,
                                    fontSize = 12.sp,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }

                    item {
                        // Stadium Info
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier.padding(10.dp),
                                text = "Stadium",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                modifier = Modifier.padding(10.dp),
                                text = club.stadium,
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                fontSize = 12.sp,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }

                    item {
                        // Trophy Section
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .padding(5.dp)
                        ) {
                            TrophyBox(
                                modifier = Modifier.weight(1f),
                                name = club.epl,
                                image = R.drawable.epl_trophy
                            )
                            TrophyBox(
                                modifier = Modifier.weight(1f),
                                name = club.ucl,
                                image = R.drawable.ucl_trophy
                            )
                            TrophyBox(
                                modifier = Modifier.weight(1f),
                                name = club.fa,
                                image = R.drawable.fa_trophy
                            )
                        }
                    }

                    item {
                        // Description Section
                        Column(
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = club.name,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                text = club.desc,
                                color = Color.Black,
                                textAlign = TextAlign.Justify,
                                fontSize = 12.sp,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }

            // Favorite Button Fixed at Bottom
            FavoriteButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter) // Keep button fixed
                    .fillMaxWidth(),
                club = club,
                detailViewModel = viewModel
            )
        }
    }
}


@Composable
@Preview
fun DetailScreenPreview() {
    DetailScreen(
        clubId = 15,
        innerPadding = PaddingValues(0.dp)
    )
}

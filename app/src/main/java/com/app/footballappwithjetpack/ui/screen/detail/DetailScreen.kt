package com.app.footballappwithjetpack.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
){
    LocalContext.current
    val clubs by viewModel.clubs.observeAsState(emptyList())
    val isFavorite by viewModel.isFav.observeAsState(false)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(innerPadding)
    ) {
        clubs[clubId].let {
            TopNavBar(name = it.name)
            Column(
                modifier = modifier.fillMaxWidth()
            ) {
                // image with desc
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = modifier
                            .weight(1f)
                            .padding(10.dp)
                            .sizeIn(minWidth = 150.dp, minHeight = 250.dp, maxHeight = 250.dp),
                        contentScale = ContentScale.Fit,
                        painter = painterResource(id = it.poto),
                        contentDescription = ""
                    )
                    Column(
                        modifier = modifier
                            .weight(1f)
                            .height(270.dp)
                            .padding(10.dp)
                    ) {
                        Text(
                            modifier = modifier.fillMaxWidth(),
                            text = it.name,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(end = 10.dp, top = 5.dp),
                            text = it.desc,
                            color = Color.Black,
                            textAlign = TextAlign.Justify,
                            fontSize = 12.sp,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                // Stadium
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = modifier
                            .padding(10.dp),
                        text = "Stadium",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        modifier = modifier
                            .padding(10.dp),
                        text = it.stadium,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                // trophy
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(5.dp)
                ) {
                    TrophyBox(
                        modifier = modifier.weight(1f),
                        name = it.epl,
                        image = R.drawable.epl_trophy
                    )
                    TrophyBox(
                        modifier = modifier.weight(1f),
                        name = it.ucl,
                        image = R.drawable.ucl_trophy
                    )
                    TrophyBox(
                        modifier = modifier.weight(1f),
                        name = it.fa,
                        image = R.drawable.fa_trophy
                    )
                }
                // Desc
                Column(
                    modifier = modifier
                        .padding(10.dp)
                ) {
                    Text(
                        modifier = modifier.fillMaxWidth(),
                        text = it.name,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        text = it.desc,
                        color = Color.Black,
                        textAlign = TextAlign.Justify,
                        fontSize = 12.sp,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = modifier.weight(1f))
                // button favorite
                FavoriteButton(club = it, detailViewModel = viewModel)
            }
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

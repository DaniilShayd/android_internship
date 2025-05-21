package com.example.androidinternship.ui.screens.photo

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.androidinternship.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoScreen(photoId: Int) {
    val photos = remember {
        listOf(
            R.drawable.photo1,
            R.drawable.photo2,
            R.drawable.photo3,
            R.drawable.photo4,
            R.drawable.photo5,
            R.drawable.photo6,
            R.drawable.photo7,
            R.drawable.photo8,
            R.drawable.photo9
        )
    }

    val pagerState = rememberPagerState(initialPage = photoId, pageCount = { photos.size }
    )

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        Image(
            painter = painterResource(photos[page]),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}
package com.example.androidinternship.ui.screens.photo

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.androidinternship.R
import com.example.androidinternship.resources.Localization.BACK
import com.example.androidinternship.resources.Localization.IMAGE_SCREEN
import net.engawapg.lib.zoomable.*

@Composable
fun PhotoScreen(photoId: Int, navController: NavController) {
    val photos = rememberPhotoList()

    Scaffold(
        topBar = { PhotoScreenTopBar(navController) }
    ) { padding ->
        PhotoContent(
            padding = padding,
            photos = photos,
            initialPhotoId = photoId
        )
    }
}

@Composable
private fun rememberPhotoList(): List<Int> = remember {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PhotoScreenTopBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(IMAGE_SCREEN)
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = BACK
                )
            }
        }
    )
}

@Composable
private fun PhotoContent(
    padding: PaddingValues,
    photos: List<Int>,
    initialPhotoId: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        val pagerState = rememberPagerState(
            initialPage = initialPhotoId,
            pageCount = { photos.size }
        )
        val zoomState = rememberZoomState(maxScale = 5f)

        HandlePageChange(pagerState, zoomState)

        PhotoPager(
            pagerState = pagerState,
            photos = photos,
            zoomState = zoomState
        )
    }
}

@Composable
private fun HandlePageChange(
    pagerState: PagerState,
    zoomState: ZoomState
) {
    LaunchedEffect(pagerState.currentPage) {
        zoomState.reset()
    }
}

@Composable
private fun PhotoPager(
    pagerState: PagerState,
    photos: List<Int>,
    zoomState: ZoomState
) {
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        ZoomableImage(
            imageRes = photos[page],
            zoomState = zoomState
        )
    }
}

@Composable
private fun ZoomableImage(
    imageRes: Int,
    zoomState: ZoomState
) {
    Image(
        painter = painterResource(imageRes),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .zoomable(zoomState),
        contentScale = ContentScale.Fit
    )
}
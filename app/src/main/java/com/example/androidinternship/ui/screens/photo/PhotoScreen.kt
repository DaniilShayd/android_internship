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
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.androidinternship.R
import net.engawapg.lib.zoomable.*

@Composable
fun PhotoScreen(
    photoId: Int,
    navController: NavController,
    viewModel: PhotoViewModel = viewModel()
) {
    val photos = viewModel.photos

    Scaffold(
        topBar = { PhotoScreenTopBar(navController) }
    ) { padding ->
        PhotoContent(
            padding = padding,
            photos = photos,
            initialPhotoIndex = photoId
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PhotoScreenTopBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(stringResource(R.string.image_screen))
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = stringResource(R.string.back)
                )
            }
        }
    )
}

@Composable
private fun PhotoContent(
    padding: PaddingValues,
    photos: List<Int>,
    initialPhotoIndex: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        val pagerState = rememberPagerState(
            initialPage = initialPhotoIndex,
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
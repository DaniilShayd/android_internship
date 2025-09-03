package com.example.androidinternship.ui.components.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.graphics.Color
import coil.compose.AsyncImage
import com.example.androidinternship.R

@Composable
fun UIImage(
    photoUrl: String,
    onClick: () -> Unit
) {
    AsyncImage(
        model = photoUrl,
        contentDescription = null,
        modifier = Modifier
            .aspectRatio(1f)
            .padding(dimensionResource(R.dimen.padding_smallest))
            .clickable { onClick() },
        contentScale = ContentScale.Crop,
        error = androidx.compose.ui.graphics.painter.ColorPainter(Color.Gray),
        placeholder = androidx.compose.ui.graphics.painter.ColorPainter(Color.LightGray)
    )
}

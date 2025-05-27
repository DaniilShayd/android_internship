package com.example.androidinternship.ui.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import com.example.androidinternship.R
import androidx.compose.ui.res.painterResource
@Composable
fun UIImage(photoRes: Int, onClick: () -> Unit) {
    Image(
        painter = painterResource(photoRes),
        contentDescription = null,
        modifier = Modifier
            .aspectRatio(1f)
            .padding(dimensionResource(R.dimen.padding_smallest))
            .clickable {
                onClick();
            },
        contentScale = ContentScale.Crop
    )
}

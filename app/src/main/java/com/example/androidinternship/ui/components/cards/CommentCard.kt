package com.example.androidinternship.ui.components.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.androidinternship.R
import com.example.androidinternship.data.Comment

@Composable
fun CommentCard(comment: Comment) {
    Card(
        modifier = Modifier
            .padding(vertical = dimensionResource(R.dimen.padding_smallest))
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(dimensionResource(R.dimen.elevation_small))
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        ) {
            Text(
                text = comment.name ?: "",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_smallest)))
            Text(text = comment.body ?: "", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
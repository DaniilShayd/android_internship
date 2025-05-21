package com.example.androidinternship.ui.components.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.androidinternship.data.Comment
import com.example.androidinternship.resources.UIDimentions

@Composable
fun CommentCard(comment: Comment) {
    Card(
        modifier = Modifier
            .padding(vertical = UIDimentions.smallestPadding)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(UIDimentions.smallElevation)
    ) {
        Column(
            modifier = Modifier.padding(UIDimentions.mediumPadding)
        ) {
            Text(
                text = comment.userName,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
            Spacer(modifier = Modifier.height(UIDimentions.smallestPadding))
            Text(text = comment.text, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
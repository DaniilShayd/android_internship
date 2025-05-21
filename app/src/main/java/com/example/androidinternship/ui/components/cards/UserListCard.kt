package com.example.androidinternship.ui.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androidinternship.data.User
import com.example.androidinternship.resources.UIDimentions

@Composable
fun UserListCard(user: User, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = UIDimentions.mediumPadding, vertical = UIDimentions.smallPadding
            )
            .clickable {
                onClick()
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(modifier = Modifier.padding(UIDimentions.mediumPadding)) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                        shape = CircleShape
                    )
            )
            Spacer(
                modifier = Modifier.width(UIDimentions.mediumSmallPadding)
            )
            Column {
                Text(text = user.name, style = MaterialTheme.typography.bodyLarge)
                Text(text = "@${user.nickname}", style = MaterialTheme.typography.bodyMedium)
                Spacer(
                    modifier = Modifier.height(UIDimentions.smallestPadding)
                )
                Text(
                    text = "Адрес: ${user.address}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Номер: ${user.phoneNumber}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

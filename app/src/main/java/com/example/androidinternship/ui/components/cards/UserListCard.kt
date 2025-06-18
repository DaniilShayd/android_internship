package com.example.androidinternship.ui.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import com.example.androidinternship.data.User
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.toSize
import com.example.androidinternship.R
import com.example.androidinternship.animation.LocalSharedElementState

@Composable
fun UserListCard(
    user: User,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state = LocalSharedElementState.current
    var itemSize by remember { mutableStateOf(Size.Zero) }
    var itemPosition by remember { mutableStateOf(Offset.Zero) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .onGloballyPositioned { coordinates ->
                    itemSize = coordinates.size.toSize()
                    itemPosition = coordinates.localToRoot(Offset.Zero)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .onGloballyPositioned { coordinates ->
                        if (state.selectedUserId == user.id) {
                            state.avatarPosition = coordinates.localToRoot(Offset.Zero)
                            state.avatarSize = coordinates.size.toSize()
                        }
                    }
            ) {
                UserAvatar(fullName = user.name, size = 40.dp)
            }
            Spacer(modifier = Modifier.width(16.dp))
            UserInfo(user)
        }
    }
}

@Composable
private fun UserAvatar(fullName: String, size: Dp) {
    Box(
        modifier = Modifier
            .size(size)
            .background(
                MaterialTheme.colorScheme.primaryContainer,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = getInitials(fullName),
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .graphicsLayer {
                    scaleX = size.value / 100f
                    scaleY = size.value / 100f
                }
        )
    }
}

@Composable
private fun UserInfo(user: User) {
    Column {
        UserName(user.name)
        UserNickname(user.nickname)
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_smallest)))
        UserAddress(user.address)
        UserPhone(user.phoneNumber)
    }
}

@Composable
private fun UserName(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
private fun UserNickname(nickname: String) {
    Text(
        text = nickname,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
private fun UserAddress(address: String) {
    Text(
        text = "${stringResource(R.string.address)} $address",
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
private fun UserPhone(phoneNumber: String) {
    Text(
        text = "${stringResource(R.string.number)} $phoneNumber",
        style = MaterialTheme.typography.bodyMedium
    )
}

private fun getInitials(fullName: String): String {
    val names = fullName.split(" ").filter { it.isNotBlank() }
    return when {
        names.size >= 2 -> "${names[0].first()}${names[1].first()}".uppercase()
        names.size == 1 -> names[0].take(2).uppercase()
        else -> ""
    }
}
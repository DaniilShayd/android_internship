package com.example.androidinternship.ui.components.cards

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.androidinternship.data.User
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.example.androidinternship.R

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun UserListCard(
    user: User,
    onClick: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    modifier: Modifier = Modifier
) {
    with(sharedTransitionScope) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(key = "user_card_${user.id}"),
                    animatedVisibilityScope = animatedContentScope,
                    enter = fadeIn() + scaleIn(),
                    exit = fadeOut() + scaleOut()
                )
                .clickable(onClick = onClick)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                UserAvatar(
                    fullName = user.name,
                    size = 40.dp,
                    sharedTransitionScope = sharedTransitionScope,
                    animatedContentScope = animatedContentScope,
                    userId = user.id
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = user.name,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.sharedElement(
                            sharedContentState = rememberSharedContentState(key = "user_name_${user.id}"),
                            animatedVisibilityScope = animatedContentScope
                        )
                    )

                    UserNickname(user.nickname)
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_smallest)))
                    UserAddress(user.address)
                    UserPhone(user.phoneNumber)
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun UserAvatar(
    fullName: String,
    size: Dp,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    userId: Int
) {
    with(sharedTransitionScope) {
        Box(
            modifier = Modifier
                .size(size)
                .sharedElement(
                    sharedContentState = rememberSharedContentState(key = "user_avatar_${userId}"),
                    animatedVisibilityScope = animatedContentScope
                )
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
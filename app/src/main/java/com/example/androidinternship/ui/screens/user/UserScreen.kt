package com.example.androidinternship.ui.screens.user

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import com.example.androidinternship.R
import com.example.androidinternship.animation.LocalSharedElementState
import com.example.androidinternship.data.User
import com.example.androidinternship.data.users
import com.example.androidinternship.ui.components.NestedScreenAppBar
import com.example.androidinternship.ui.components.cards.CommentCard
import com.example.androidinternship.ui.composables.UIButton

@Composable
fun UserScreen(userId: Int, navController: NavHostController) {
    val state = LocalSharedElementState.current
    val user = users.find { it.id == userId }

    Column {
        NestedScreenAppBar(
            color = MaterialTheme.colorScheme.surfaceVariant,
            onBackClick = {
                state.isTransitionActive = false
                navController.popBackStack()
            }
        )

        if (user != null) {
            UserContent(user = user) { size, position ->
                state.targetAvatarSize = size
                state.targetAvatarPosition = position
            }
        } else {
            UserNotFound()
        }
    }
}

@Composable
private fun UserContent(
    user: User,
    onPositioned: (Size, Offset) -> Unit
) {
    val state = LocalSharedElementState.current
    val density = LocalDensity.current


    val targetSizePx = with(density) { 100.dp.toPx() }
    val targetSize = Size(targetSizePx, targetSizePx)

    val avatarSize by animateFloatAsState(
        targetValue = if (state.isTransitionActive) targetSize.width else state.avatarSize.width,
        animationSpec = tween(300),
        label = "avatarSize"
    )

    val avatarX by animateFloatAsState(
        targetValue = if (state.isTransitionActive) 0f else state.avatarPosition.x,
        animationSpec = tween(300),
        label = "avatarX"
    )

    val avatarY by animateFloatAsState(
        targetValue = if (state.isTransitionActive) 0f else state.avatarPosition.y,
        animationSpec = tween(300),
        label = "avatarY"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant)
        )

        Box(
            modifier = Modifier

                .size(with(density) { avatarSize.toDp() })
                .offset(
                    x = with(density) { avatarX.toDp() },
                    y = with(density) { avatarY.toDp() } - 50.dp
                )
                .onGloballyPositioned { coordinates ->
                    if (!state.isTransitionActive) {
                        onPositioned(
                            coordinates.size.toSize(),
                            coordinates.localToRoot(Offset.Zero)
                        )
                        state.isTransitionActive = true
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            UserAvatar(
                fullName = user.name,
                size = with(density) { avatarSize.toDp() }
            )
        }

        UserName(name = user.name)
        UserCommentSection(user)
    }
}

@Composable
private fun UserHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant)
    )
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
private fun UserName(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.headlineSmall,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun UserCommentSection(user: User) {
    var commentsIsOpened by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium_large)))
        Text(
            text = stringResource(R.string.comments),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )

        val comments = user.comments

        if (!commentsIsOpened) {
            comments.take(2).forEach { comment ->
                CommentCard(comment = comment)
            }

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium_large)))

            UIButton(
                text = stringResource(R.string.show_comments),
                onClick = { commentsIsOpened = true }
            )
        } else {
            comments.forEach { comment ->
                CommentCard(comment = comment)
            }

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium_large)))

            UIButton(
                text = stringResource(R.string.hide_comments),
                onClick = { commentsIsOpened = false }
            )
        }
    }
}

@Composable
private fun UserNotFound() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(R.string.user_not_found),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
    }
}

private fun getInitials(fullName: String): String {
    val names = fullName.split(" ").filter { it.isNotBlank() }
    return when {
        names.size >= 2 -> "${names[0].first()}${names[1].first()}".uppercase()
        names.size == 1 -> names[0].take(2).uppercase()
        else -> ""
    }
}
package com.example.androidinternship.navigation

import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.androidinternship.R
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable

sealed class TabItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {

    class Posts(title: String) : TabItem(
        route = NavRoutes.POSTS_MAIN,
        title = title,
        icon = Icons.Default.FormatListNumbered
    )

    class Photos(title: String) : TabItem(
        route = NavRoutes.ALBUMS_MAIN,
        title = title,
        icon = Icons.Default.Image
    )

    class Todos(title: String) : TabItem(
        route = NavRoutes.TODOS_MAIN,
        title = title,
        icon = Icons.Default.Check
    )

    class Users(title: String) : TabItem(
        route = NavRoutes.USERS_MAIN,
        title = title,
        icon = Icons.Default.Person
    )

    companion object {
        @Composable
        fun getTabs(): List<TabItem> {
            return listOf(
                Posts(stringResource(R.string.posts_tab)),
                Photos(stringResource(R.string.albums_tab)),
                Todos(stringResource(R.string.todos_tab)),
                Users(stringResource(R.string.users_tab))
            )
        }
    }
}
package com.example.androidinternship.navigation


import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector


sealed class TabItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Posts : TabItem(
        route = NavRoutes.POSTS_MAIN,
        title = "Posts",
        icon = Icons.Default.FormatListNumbered
    )

    data object Photos : TabItem(
        route = NavRoutes.ALBUMS_MAIN,
        title = "Photos",
        icon = Icons.Default.Image
    )

    data object Todos : TabItem(
        route = NavRoutes.TODOS_MAIN,
        title = "Todos",
        icon = Icons.Default.Check
    )

    data object Users : TabItem(
        route = NavRoutes.USERS_MAIN,
        title = "Users",
        icon = Icons.Default.Person
    )

    companion object {
        fun getTabs(): List<TabItem> {
            return listOf(Posts, Photos, Todos, Users)
        }
    }
}
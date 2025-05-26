package com.example.androidinternship.navigation


import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.androidinternship.resources.Localization.ALBUMS_TAB
import com.example.androidinternship.resources.Localization.POSTS_TAB
import com.example.androidinternship.resources.Localization.TODOS_TAB
import com.example.androidinternship.resources.Localization.USERS_TAB


sealed class TabItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Posts : TabItem(
        route = NavRoutes.POSTS_MAIN,
        title = POSTS_TAB,
        icon = Icons.Default.FormatListNumbered
    )

    data object Photos : TabItem(
        route = NavRoutes.ALBUMS_MAIN,
        title = ALBUMS_TAB,
        icon = Icons.Default.Image
    )

    data object Todos : TabItem(
        route = NavRoutes.TODOS_MAIN,
        title = TODOS_TAB,
        icon = Icons.Default.Check
    )

    data object Users : TabItem(
        route = NavRoutes.USERS_MAIN,
        title = USERS_TAB,
        icon = Icons.Default.Person
    )

    companion object {
        fun getTabs(): List<TabItem> {
            return listOf(Posts, Photos, Todos, Users)
        }
    }
}
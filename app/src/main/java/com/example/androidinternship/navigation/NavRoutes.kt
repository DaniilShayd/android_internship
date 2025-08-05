package com.example.androidinternship.navigation

import com.example.androidinternship.navigation.NavElements.ALBUM_ID
import com.example.androidinternship.navigation.NavElements.PHOTO_INDEX
import com.example.androidinternship.navigation.NavElements.POST_ID
import com.example.androidinternship.navigation.NavElements.USER_ID


object NavRoutes {
    // Main tabs (root screens with BottomTabBar)
    const val POSTS = "posts"
    const val ALBUMS = "albums"
    const val TODOS = "todos"
    const val USERS = "users"

    // Nested routes (child screens without BottomTabBar)
    const val SPLASH = "splash"
    
    const val POSTS_MAIN = "posts_main"
    const val POST_DETAIL = "post_detail/{${POST_ID}}"

    const val ALBUMS_MAIN = "albums_main"
    const val ALBUM = "album/{${ALBUM_ID}}"
    const val PHOTO = "photo/{${ALBUM_ID}}/{${PHOTO_INDEX}}"

    const val TODOS_MAIN = "todos_main"
    const val TODO_DETAIL_WITH_INDEX = "todos/detail?index={index}"

    const val USERS_MAIN = "users_main"
    const val USER_DETAIL = "user_detail/{${USER_ID}}"

    // List of root routes where BottomTabBar should be visible
    val rootRoutes = setOf(POSTS_MAIN, ALBUMS_MAIN, TODOS_MAIN, USERS_MAIN)
}
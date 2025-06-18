package com.example.androidinternship.animation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size

class SharedElementState {
    var isTransitionActive by mutableStateOf(false)
    var selectedUserId by mutableStateOf<Int?>(null)

    var avatarPosition by mutableStateOf(Offset.Zero)
    var avatarSize by mutableStateOf(Size.Zero)

    var targetAvatarPosition by mutableStateOf(Offset.Zero)
    var targetAvatarSize by mutableStateOf(Size.Zero)
}

val LocalSharedElementState = staticCompositionLocalOf { SharedElementState() }
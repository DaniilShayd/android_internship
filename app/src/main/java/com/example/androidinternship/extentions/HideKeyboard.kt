package com.example.androidinternship.extentions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.*
import androidx.compose.ui.platform.*

fun Modifier.hideKeyboardOnTap(): Modifier = composed {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    this.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        keyboardController?.hide()
        focusManager.clearFocus()
    }
}
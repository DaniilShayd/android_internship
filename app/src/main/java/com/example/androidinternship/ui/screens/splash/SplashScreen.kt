package com.example.androidinternship.ui.screens.splash

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androidinternship.R
import kotlinx.coroutines.delay

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun SplashScreen(
    onAnimationFinished: () -> Unit
) {
    val offsetX = remember { Animatable(0f) }
    val offsetY = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        delay(250)

        animateAndReturn(offsetY, -100f)
        animateAndReturn(offsetY, 100f)
        animateAndReturn(offsetX, -100f)
        animateAndReturn(offsetX, 100f)

        onAnimationFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.offset(
                x = offsetX.value.dp,
                y = offsetY.value.dp
            ).padding(64.dp)
        )
    }
}

private suspend fun animateAndReturn(
    animatable: Animatable<Float, *>,
    targetValue: Float
) {
    animatable.animateTo(targetValue, animationSpec = tween(250))
    animatable.animateTo(0f, animationSpec = tween(250))
}
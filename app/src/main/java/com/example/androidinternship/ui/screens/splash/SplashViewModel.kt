package com.example.androidinternship.ui.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class SplashViewModel : ViewModel() {
    private val _animationFinished = MutableSharedFlow<Unit>(replay = 0)
    val animationFinished: SharedFlow<Unit> = _animationFinished

    suspend fun playAnimation(
        offsetX: Animatable<Float, AnimationVector1D>,
        offsetY: Animatable<Float, AnimationVector1D>
    ) {
        delay(250)

        animateAndReturn(offsetY, -100f)
        animateAndReturn(offsetY, 100f)
        animateAndReturn(offsetX, -100f)
        animateAndReturn(offsetX, 100f)

        _animationFinished.emit(Unit)
    }

    private suspend fun animateAndReturn(
        animatable: Animatable<Float, *>,
        targetValue: Float
    ) {
        animatable.animateTo(targetValue, animationSpec = tween(250))
        animatable.animateTo(0f, animationSpec = tween(250))
    }
}

package com.example.a2d_golf.userinterface

import android.util.Log
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.a2d_golf.GameState
import com.example.a2d_golf.GameStatus
import com.example.a2d_golf.R
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun StartPage(modifier: Modifier, gameState: MutableStateFlow<GameState>) {
    val offset = Offset(1.0f, 6.0f)

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val animatedValue by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Box(
        Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    gameState.value.status = GameStatus.GAME
                }
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_new),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.press_to_start),
                color = Color(0xFFE2E5DE),
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 30.sp,
                    shadow = Shadow(
                        color = Color(0xFFF5F5F5), offset = offset, blurRadius = 3f
                    )
                ),
                modifier = Modifier.graphicsLayer {
                    translationY = animatedValue * 40
                }
            )
        }
    }
}

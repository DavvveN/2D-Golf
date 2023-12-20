package com.example.a2d_golf

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

@Composable
fun Ball(
    ballState : BallState,

) {
    Image(
        painter = painterResource(id = R.drawable.ball),
        contentDescription = null
    )
}
package com.example.a2d_golf.userinterface

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.a2d_golf.GameStatus
import com.example.a2d_golf.GameViewModel

@Composable
fun PromptRestart(viewModel: GameViewModel) {
    val _gS = viewModel._gameState
    val gS = _gS.collectAsState()
    Row(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    _gS.value = gS.value.copy(status = GameStatus.SETTINGS, promptRestart = true)
                }
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,

        ) {
        Column {
            Text(
                text = "LEVEL FAILED!",
                style = TextStyle(
                    fontSize = 30.sp,
                    shadow = Shadow(
                        color = Color(0xFFF5F5F5), blurRadius = 3f
                    )
                )
            )
            Text(
                text = "PRESS ANYWHERE TO RESTART",
                style = TextStyle(
                    fontSize = 18.sp,
                    shadow = Shadow(
                        color = Color(0xFFF5F5F5), blurRadius = 3f
                    )
                )
            )
        }
    }
}
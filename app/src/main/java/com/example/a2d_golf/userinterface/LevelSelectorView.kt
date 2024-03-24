package com.example.a2d_golf.userinterface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.a2d_golf.GameViewModel


@Composable
fun LevelSelectorView(viewModel: GameViewModel) {
    val _gS = viewModel._gameState
    val gS = _gS.collectAsState()
    val levels = gS.value.levelData.levels

    Box(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            for (l in levels.indices) {
                Button(
                    onClick = {
                        _gS.value = gS.value.copy(currentLevel = l)
                        viewModel.restart()
                    },
                    modifier = Modifier
                        .padding(10.dp)
                        .background(Color.Cyan)
                ) {
                    Text(l.toString())
                }
            }
        }
    }
}
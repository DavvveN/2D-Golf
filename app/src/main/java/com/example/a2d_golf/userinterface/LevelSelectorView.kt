package com.example.a2d_golf.userinterface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a2d_golf.GameViewModel


@Composable
fun LevelSelectorView(viewModel: GameViewModel) {
    val _gS = viewModel._gameState
    val gS = _gS.collectAsState()
    val levels = gS.value.levelData.levels

    Box(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .fillMaxSize()
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(30.dp),

        ) {
            for (l in levels.indices) {
                Button(
                    onClick = {
                        _gS.value = gS.value.copy(currentLevel = l)
                        viewModel.restart()
                    },
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                ) {
                    Text(
                        text = l.toString(),
                        style = androidx.compose.ui.text.TextStyle(
                            fontSize = 30.sp,
                            shadow = Shadow(
                                color = Color(0xFFF5F5F5), blurRadius = 3f
                            )
                        ),
                        modifier = Modifier
                            .padding(50.dp)
                    )
                }
            }
        }
    }
}
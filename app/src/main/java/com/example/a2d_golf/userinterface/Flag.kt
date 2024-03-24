package com.example.a2d_golf.userinterface

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.a2d_golf.GameState
import com.example.a2d_golf.GameViewModel
import com.example.a2d_golf.R

@Composable
fun Flag(viewModel : GameViewModel, gameState: GameState) {
    val cl = gameState.levelData.levels[gameState.currentLevel]

    for(l in cl){
        if(l is Goal){
            Canvas(modifier = Modifier.fillMaxSize()){
                drawLine(
                    color = Color.Black,
                    start = Offset((l.p1.x +(l.p2.x - l.p1.x) / 2),l.p1.y),
                    end = Offset((l.p1.x +(l.p2.x - l.p1.x) / 2),l.p1.y - 150f),
                    strokeWidth = 15f
                )

                drawLine(
                    color = Color.Black,
                    start = Offset((l.p1.x +(l.p2.x - l.p1.x) / 2),l.p1.y - 150f),
                    end = Offset((l.p1.x +(l.p2.x - l.p1.x) / 2) + 80f,l.p1.y - 110f),
                    strokeWidth = 15f
                )

                drawLine(
                    color = Color.Black,
                    start = Offset((l.p1.x +(l.p2.x - l.p1.x) / 2) + 80f,l.p1.y - 110f),
                    end = Offset((l.p1.x +(l.p2.x - l.p1.x) / 2),l.p1.y - 80f),
                    strokeWidth = 15f
                )
            }
        }
    }
}
package com.example.a2d_golf.userinterface

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

@Composable
fun DrawMap(level: List<Drawable>) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        for (d in level) {
            if (d is Line) {
                drawLine(
                    start = Offset(x = d.p1.x, y = d.p1.y),
                    end = Offset(x = d.p2.x, y = d.p2.y),
                    color = Color.Green,
                    strokeWidth = 10f
                )

                //TODO Design LEVEL
                if (d.p1.y == d.p2.y) {
                    drawLine(
                        start = Offset(x = d.p1.x, y = d.p1.y + 20),
                        end = Offset(x = d.p2.x, y = d.p2.y + 20),
                        color = Color(0, 100, 0),
                        strokeWidth =30f
                    )
                }else if (d.p1.x == d.p2.x){
                    drawLine(
                        start = Offset(x = d.p1.x - 20, y = d.p1.y ),
                        end = Offset(x = d.p2.x - 20, y = d.p2.y ),
                        color = Color(0, 100, 0),
                        strokeWidth =30f
                    )
                }
            }
        }


    }
}
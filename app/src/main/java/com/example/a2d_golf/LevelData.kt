package com.example.a2d_golf

import androidx.compose.ui.platform.LocalConfiguration
import com.example.a2d_golf.userinterface.Drawable
import com.example.a2d_golf.userinterface.Goal
import com.example.a2d_golf.userinterface.Line
import com.example.a2d_golf.userinterface.Point

data class LevelData(
    var levels: List<List<Drawable>> = listOf(

        listOf(
            Line(Point(0f, 600f), Point(565f, 600f)),
            Line(Point(565f, 600f), Point(852f, 520f)),
            Line(Point(852f, 520f), Point(885f, 300f)),
            Line(Point(885f, 300f), Point(1000f, 300f)),
            Line(Point(1000f, 300f), Point(1100f, 475f)),
            Line(Point(1100f, 475f), Point(1500f, 475f)),
            Line(Point(1500f, 475f), Point(1600f, 600f)),
            Line(Point(1600f, 600f), Point(2000f, 600f)),
            Goal(Point(2000f, 600f), Point(2200f, 600f)),
            Line(Point(2200f, 600f), Point(2500f, 600f)),
        ),

        listOf(
            Line(Point(0f, 800f), Point(565f, 800f)),
            Line(Point(565f, 800f), Point(600f, 850f)),
            Goal(Point(600f, 850f), Point(800f, 850f)),
            Line(Point(800f, 850f), Point(900f, 400f)),
            Line(Point(900f, 400f), Point(1100f, 300f)),
            Line(Point(1100f, 300f), Point(1200f, 200f)),
            Line(Point(1200f, 200f), Point(1400f, 200f)),
            Line(Point(1400f, 200f), Point(1700f, 600f)),
            Line(Point(1400f, 200f), Point(1700f, 600f)),
            Line(Point(1700f, 600f), Point(2000f, 600f)),
            Goal(Point(2000f, 600f), Point(2200f, 600f)),
            Line(Point(2200f, 600f), Point(2500f, 600f)),

            ),
    )
)
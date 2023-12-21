package com.example.a2d_golf
data class LevelData(
    var firstLevel : List<Drawable> = listOf(
        Line(0f, 735f, 565f, 735f),
        Line(565f, 735f, 852f, 520f),
        Circle(10f,-271f,72f),
        Line(885f,534f,885f,735f),
        Line(885f,735f,1176f,735f),
        Line(1176f,735f,1176f,475f),
        Circle(7f,62f,0f),
        Line(1200f,456f, 1544f,456f),
        Circle(7f,420f,0f),
        Line(1568f,465f,1568f,825f),
        Line(1568f,825f,1798f,825f),
        Line(1798f,825f,1798f,695f),
        Circle(9f,689f,228f),
        Line(1825f,677f, 2264f,677f)

    )
) {
}
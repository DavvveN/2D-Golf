package com.example.a2d_golf
data class LevelData(
    var firstLevel : List<Drawable> = listOf(
        Line(Point(0f, 735f), Point(565f, 735f)),
        Line(Point(565f, 735f), Point(852f, 520f)),
        Line(Point(885f,534f),Point(885f,735f)),
        Line(Point(885f,735f),Point(1176f,735f)),
        Line(Point(1176f,735f),Point(1176f,475f)),
        Line(Point(1200f,456f), Point(1544f,456f)),
        Line(Point(1568f,465f),Point(1568f,825f)),
        Line(Point(1568f,825f),Point(1798f,825f)),
        Line(Point(1798f,825f),Point(1798f,695f)),
        Line(Point(1825f,677f), Point(2264f,677f))

    )
) {
}
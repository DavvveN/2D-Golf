package com.example.a2d_golf


enum  class Shape{
    SQUARE,
    TRIANGLE
}
data class LevelData(
    var firstLevel : List<List<GridComponent>> = listOf(
        listOf(GridComponent("first_1", Shape.SQUARE, false)),
        listOf(GridComponent("first_3", Shape.SQUARE, false), GridComponent("first_2", Shape.TRIANGLE, false)),
        listOf(GridComponent("first_4", Shape.SQUARE, false)),
        listOf(GridComponent("first_5", Shape.SQUARE, false)),
        listOf(GridComponent("first_6", Shape.SQUARE, false)),
        listOf(GridComponent("first_7", Shape.SQUARE, true)),
    )
) {
}
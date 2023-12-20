package com.example.a2d_golf

import androidx.compose.ui.unit.Dp

class GridComponent (
    private val id: String,
    private val shape : Shape,
    private val isLast : Boolean
){
    fun getId() : String{
        return id;
    }

    fun getShape() : Shape{
        return shape;
    }

    fun getIsLast() : Boolean{
        return isLast;
    }
}
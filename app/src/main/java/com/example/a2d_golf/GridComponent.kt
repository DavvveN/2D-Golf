package com.example.a2d_golf

class GridComponent (private val id: String, private val shape : Shape, private val isLast : Boolean){
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
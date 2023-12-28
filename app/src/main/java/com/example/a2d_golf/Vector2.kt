package com.example.a2d_golf

import kotlin.math.pow
import kotlin.math.sqrt

class Vector2(var xPos : Float, var yPos : Float) {
    object VectorConst{
        val DOWN : Vector2 = Vector2(0f,-1f)
        val UP : Vector2 = Vector2(0f,1f)
        val LEFT : Vector2 = Vector2(-1f,0f)
        val RIGHT : Vector2 = Vector2(0f,0f)
        val EMPTY : Vector2 = Vector2(0f,0f)
    }

    //FUNCTION TO CALCULATE MAGNITUDE OF A VECTOR
    fun magnitude() : Float{
        return sqrt(xPos.toDouble().pow(2.0) + yPos.toDouble().pow(2.0)).toFloat()
    }

    //NORMALIZE THE VECTOR i.e. MAKE IT A UNIT VECTOR
    fun normalize() : Vector2{
        return scale(1/magnitude())
    }

    //MULTIPLY THE VECTOR WITH A SCALAR
    fun scale(factor : Float) : Vector2{
        return Vector2(xPos * factor,yPos * factor )
    }

    //PARAMETER : LIST allowing addition of multiple vectors
    fun add(v2 : List<Vector2>) : Vector2{
        for(v in v2){
            xPos += v.xPos
            yPos += v.yPos
        }
        return Vector2(xPos,yPos)
    }
}
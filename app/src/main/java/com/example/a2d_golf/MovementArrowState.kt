package com.example.a2d_golf

import android.util.Log
import com.example.a2d_golf.consts.PhysicsConst
import kotlinx.coroutines.flow.MutableStateFlow

data class MovementArrowState(
    var position: Vector2,
    var display: Boolean,
    val _bState : MutableStateFlow<BallState>,
    val physicsConst: PhysicsConst = PhysicsConst(),
){
    fun interaction(position : Vector2){
        position.also{this.position = it}
    }

    fun launchBall(){
        _bState.value.userForce = position.sub(_bState.value.position)
        display = false

        //Log.i("abcdef" , "x = " + _bState.value.userForce.xPos)
        //Log.i("abcdef" , "y = " + _bState.value.userForce.yPos)
        //Log.i("abcdef" , "-------------------------------------")


    }
}
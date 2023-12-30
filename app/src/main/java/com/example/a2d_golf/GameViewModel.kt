package com.example.a2d_golf

import android.app.Application
import android.graphics.RectF
import android.util.Log
import android.view.Gravity
import androidx.compose.runtime.Composable
import androidx.lifecycle.AndroidViewModel
import androidx.room.util.copy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class GameStatus{
    FALLING,
    ON_GROUND,
    IN_MOTION
}

data class GameState(
    var status :GameStatus = GameStatus.FALLING,
    val ballState: BallState = BallState(velocity = Vector2(0f,0f),position = Vector2(400f,200f))
){
    fun toggleStatus(a : GameStatus){
        status = a;
    }
}
class GameViewModel(application: Application) : AndroidViewModel(application) {
    private var prevTime = 0L

    //                        LEARNING KOTLIN JETPACK:
    // ***********************************************************************
    // _gameState and _bState are mutableStateFlows and thus are changeable
    // gameState and bState derive from _gameState and _bState and are read only
    // We can thus control changeability or readability in a given function
    // ***********************************************************************


    private var _gameState = MutableStateFlow(GameState())
    var gameState = this._gameState.asStateFlow()
    private var _bState = MutableStateFlow(this.gameState.value.ballState)
    var bState = _bState.asStateFlow()

    private val physicsConst = PhysicsConst()
    fun update(deltaTime : Float){

        when (gameState.value.status){
            GameStatus.FALLING -> handleFalling(deltaTime)
            GameStatus.IN_MOTION -> handleInMotion()
            GameStatus.ON_GROUND -> handleOnGround()
        }
    }

    private fun handleFalling(deltaTime : Float){

        val gravityForce = Vector2.VectorConst.UP.scale(physicsConst.GRAVITY)
        val newVelocity = bState.value.velocity.add(gravityForce.scale(deltaTime))

        // Change position in relation to velocity * time
        val newPosition = bState.value.position.add(newVelocity.scale(deltaTime))
        _bState.value = BallState(velocity = newVelocity.copy(), position = newPosition.copy())
        Log.i("abcdefg",newPosition.yPos.toString())

    }

    private fun handleOnGround (){

    }

    private fun handleInMotion(){

    }
}
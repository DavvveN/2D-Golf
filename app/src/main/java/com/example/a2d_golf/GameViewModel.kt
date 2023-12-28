package com.example.a2d_golf

import android.app.Application
import android.graphics.RectF
import android.util.Log
import android.view.Gravity
import androidx.compose.runtime.Composable
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class GameStatus{
    STOPPED,
    RUNNING;

}

data class GameState(
    var status :GameStatus = GameStatus.STOPPED,
    val ballState: BallState = BallState(velocity = Vector2(0f,0f),position = Vector2(850f,200f))
){
    fun toggleStatus(a : GameStatus){
        status = a;
    }
}
class GameViewModel(application: Application) : AndroidViewModel(application) {
    private val _gameState = MutableStateFlow(GameState())
    val gameState = _gameState.asStateFlow()
    private val _bState = MutableStateFlow(gameState.value.ballState)
    val bState = _bState.asStateFlow()

    private val physicsConst = PhysicsConst()

    @Composable
    fun startGame(){
        GameView()
    }

    fun update(deltaTime : Long){
        //MULTIPLY THE VELOCITY VECTOR WITH GRAVITY
        bState.value.velocity = bState.value.velocity.add(listOf(Vector2.VectorConst.UP.scale(physicsConst.GRAVITY * deltaTime)))

        //CHANGE POSITION IN RELATION TO VELOCITY * TIME
        bState.value.position = bState.value.position.add(listOf(bState.value.velocity.scale(deltaTime * 1f)))
        //TODO CHECK COLLISION
    }
}
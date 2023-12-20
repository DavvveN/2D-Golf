package com.example.a2d_golf

import android.app.Application
import android.graphics.RectF
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
    val ballState: BallState = BallState(RectF())
){
    fun toggleStatus(a : GameStatus){
        status = a;
    }
}
class GameViewModel(application: Application) : AndroidViewModel(application) {
    private val _gameState = MutableStateFlow(GameState())
    val gameState = _gameState.asStateFlow()

    @Composable
    fun startGame(){
        GameView()
    }
}
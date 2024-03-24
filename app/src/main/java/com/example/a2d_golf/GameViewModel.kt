package com.example.a2d_golf

import android.app.Application
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.AndroidViewModel
import com.example.a2d_golf.consts.MiscConst
import com.example.a2d_golf.consts.PhysicsConst
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class GameStatus {
    START,
    GAME,
    SETTINGS
}

data class GameState(
    var status: GameStatus = GameStatus.START,
    val ballState: BallState = BallState(
        velocity = Vector2(0f, 0f),
        position = Vector2(400f, 600f),
        userForce = Vector2(0f, 0f)
    ),
    val levelData: LevelData
)

data class SettingsState(
    var volume: Float = 0.5f
)

class GameViewModel(application: Application) : AndroidViewModel(application) {
    var _gameState = MutableStateFlow(GameState(levelData = LevelData()))
    var gameState = this._gameState.asStateFlow()
    private var _bState = MutableStateFlow(this.gameState.value.ballState)
    var bState = _bState.asStateFlow()
    var _settingState = MutableStateFlow(SettingsState())
    var settingsState = _settingState.asStateFlow()
    val miscConst: MiscConst = MiscConst()


    private val physicsConst = PhysicsConst()

    private var _movementArrowState = MutableStateFlow(
        MovementArrowState(
            position = bState.value.position,
            display = true,
            _bState = this._bState,
            physicsConst = PhysicsConst()
        )
    )
    var movementArrowState = _movementArrowState.asStateFlow()

    fun update(deltaTime: Float) {
        handleMovement(deltaTime)
    }

    fun restart() {
        _bState.value = bState.value.copy(
            velocity = Vector2(0f, 0f),
            position = Vector2(400f, 600f),
            userForce = Vector2(0f, 0f)
        )

        _movementArrowState.value = movementArrowState.value.copy(
            display = true,
            position = bState.value.position
        )

        startGame()
    }

    fun startGame() {
        _gameState.value = gameState.value.copy(status = GameStatus.GAME)
    }

    fun showSettings() {
        _gameState.value = gameState.value.copy(status = GameStatus.SETTINGS)
    }

    //Handles more than falling: also applies a user force
    private fun handleMovement(deltaTime: Float) {

        val gravityForce = Vector2.VectorConst.UP.scale(physicsConst.GRAVITY)
        val newVelocity = bState.value.velocity.add(gravityForce.scale(deltaTime))

        val uF = bState.value.userForce.scale(physicsConst.USERFACTOR).scale(deltaTime)
        newVelocity.add(uF)

        _bState.value.userForce = Vector2.VectorConst.EMPTY

        // Change position in relation to velocity * time
        val newPosition = bState.value.position.add(newVelocity.scale(deltaTime))
        var prevPos = bState.value.prevPos
        val pP: ArrayList<Vector2> = bState.value.prevPositions

        if (bState.value.prevPos.xPos + miscConst.DISTANCE_BETWEEN_BALLS < newPosition.xPos) {
            pP.add(newPosition)
            prevPos = newPosition
        }

        _bState.value = BallState(
            velocity = newVelocity.copy(),
            position = newPosition.copy(),
            userForce = bState.value.userForce,
            prevPos = prevPos,
            prevPositions = pP
        )
        ResolveCollision()
    }

    //Detects and resolves collisions
    private fun ResolveCollision() {
        for (d in gameState.value.levelData.firstLevel) {
            //Check which line share the same x value ie. which line is under the ball
            if (d.collidesWith(bState)) {
                _bState.value = d.handleCollision(_bState).value

                //TODO SOUND EFFECT IF DISPLAY = OFF
            }
        }
    }

}
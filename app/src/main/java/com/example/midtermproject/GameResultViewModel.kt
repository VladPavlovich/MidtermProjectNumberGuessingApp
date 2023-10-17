package com.example.midtermproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GameResultViewModel(private val dao: GameResultDao) : ViewModel() {

    var playerName = ""
    var score = 0

    val gameResults: LiveData<List<GameResult>> = dao.getAllGameResults()
    private val _navigateToGameResult = MutableLiveData<Long?>()

    val navigateToGameResult: LiveData<Long?>
        get() = _navigateToGameResult

    // Creates new game result
    fun addGameResult() {
        viewModelScope.launch {
            Log.i("GameResultViewModel", "addGameResult: $playerName with score $score.")
            val gameResult = GameResult()
            gameResult.playerName = playerName
            gameResult.score = score
            dao.insert(gameResult)
        }
    }

    // Game result clicked
    fun onGameResultClicked(resultId: Long) {
        _navigateToGameResult.value = resultId
    }

    // Game result navigated
    fun onGameResultNavigated() {
        _navigateToGameResult.value = null
    }

    fun deleteGameResult(resultId: Long) {
        viewModelScope.launch {
            val result = GameResult()
            result.resultId = resultId
            dao.delete(result)

        }
    }
}

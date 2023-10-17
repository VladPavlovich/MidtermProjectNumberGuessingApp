package com.example.midtermproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GameResultViewModelFactory(private val dao: GameResultDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameResultViewModel::class.java)){
            return GameResultViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}

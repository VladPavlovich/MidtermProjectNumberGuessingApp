package com.example.midtermproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel(){
    val attempts : MutableLiveData<Int> = MutableLiveData(0)

    fun incrementAttempts() {
        val currentAttempts = attempts.value ?: 0
        attempts.value = currentAttempts + 1
    }

    fun resetAttempts() {
        attempts.value = 0
    }



}
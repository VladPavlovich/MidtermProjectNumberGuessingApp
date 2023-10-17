package com.example.midtermproject

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface GameResultDao {

    @Insert
    suspend fun insert(gameResult: GameResult)

    @Update
    suspend fun update(gameResult: GameResult)

    @Delete
    suspend fun delete(gameResult: GameResult)

    @Query("SELECT * FROM game_result_table WHERE resultId = :key")
    fun get(key: Long): LiveData<GameResult>

    @Query("SELECT * FROM game_result_table ORDER BY resultId DESC")
    fun getAllGameResults(): LiveData<List<GameResult>>

}
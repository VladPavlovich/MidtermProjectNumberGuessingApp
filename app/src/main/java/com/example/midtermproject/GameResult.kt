package com.example.midtermproject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "game_result_table")
data class GameResult(
    @PrimaryKey(autoGenerate = true)
    var resultId: Long = 0L,
    @ColumnInfo(name = "player_name")
    var playerName: String = "",
    @ColumnInfo(name = "score")
    var score: Int = 0
)

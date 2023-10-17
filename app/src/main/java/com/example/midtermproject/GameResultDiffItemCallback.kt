package com.example.midtermproject

import androidx.recyclerview.widget.DiffUtil

class GameResultDiffItemCallback : DiffUtil.ItemCallback<GameResult>() {
    override fun areItemsTheSame(oldItem: GameResult, newItem: GameResult): Boolean {
        return oldItem.resultId == newItem.resultId
    }

    override fun areContentsTheSame(oldItem: GameResult, newItem: GameResult): Boolean {
        return oldItem == newItem
    }
}
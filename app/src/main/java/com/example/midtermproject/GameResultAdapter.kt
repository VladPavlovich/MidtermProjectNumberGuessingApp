package com.example.midtermproject
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ExpandableListView.OnChildClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.RecyclerView
import com.example.midtermproject.databinding.ResultItemBinding


class GameResultAdapter(private val data: List<GameResult>, private val deleteClickListener: (resultId: Long) -> Unit)
    : RecyclerView.Adapter<GameResultAdapter.GameResultViewHolder>() {


    var gameResults: MutableList<GameResult> = data.toMutableList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class GameResultViewHolder(private val binding: ResultItemBinding)
        : RecyclerView.ViewHolder(binding.root) {


        companion object {
            fun inflateFrom(parent: ViewGroup): GameResultViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ResultItemBinding.inflate(layoutInflater, parent, false)
                return GameResultViewHolder(binding)
            }
        }

        /**
         * The function that binds the data to the view holder
         * @param GameResult is the item for the game result
         * @param deleteClickListener The delete click listener for the game result
         */

        fun bind(item: GameResult,deleteClickListener: (resultId: Long) -> Unit) {
            binding.gameResult = item
            binding.deleteButton.setOnClickListener{
                val context = it.context
                val fragmentManager = (context as AppCompatActivity).supportFragmentManager

                val dialog = DeleteConfirmationDialogFragment {
                    deleteClickListener(item.resultId)
                }
                dialog.show(fragmentManager, "deleteConfirmation")

            }
            // Any additional binding logic can be added here
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : GameResultViewHolder = GameResultViewHolder.inflateFrom(parent)


    override fun onBindViewHolder(holder: GameResultViewHolder, position: Int) {
        val item = gameResults[position]
        holder.bind(item, deleteClickListener)
    }


    override fun getItemCount(): Int {
        return gameResults.size
    }
}

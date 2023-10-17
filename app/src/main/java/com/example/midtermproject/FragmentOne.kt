package com.example.midtermproject

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.midtermproject.databinding.FragmentOneBinding
import kotlin.random.Random

class FragmentOne : Fragment() {

    private var _binding: FragmentOneBinding? = null
    private val binding get() = _binding!!
    private val ViewModel: SharedViewModel by activityViewModels()
    private lateinit var viewModel: GameResultViewModel


    private var randomNumber = 0
    private var numberOfAttempts = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentOneBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = GameResultDatabase.getInstance(application).gameResultDao
        val viewModelFactory = GameResultViewModelFactory(dao)
        viewModel = ViewModelProvider(this, viewModelFactory).get(GameResultViewModel::class.java)

//click listeners for buttons that increment the number in the edit text
        binding.minusButton.setOnClickListener {
            val currentNumber = binding.numberEditText.text.toString().toIntOrNull() ?: 0
            val updatedNumber = currentNumber - 1
            binding.numberEditText.setText(updatedNumber.toString())
        }

        binding.plusButton.setOnClickListener {
            val currentNumber = binding.numberEditText.text.toString().toIntOrNull() ?: 0
            val updatedNumber = currentNumber + 1
            binding.numberEditText.setText(updatedNumber.toString())
        }
//creates random number
        randomNumber = Random.nextInt(1, 101)
        Log.i("random number", randomNumber.toString())

//click listener for ok button
        binding.okButton.setOnClickListener{
            val userGuess = binding.numberEditText.text.toString().toIntOrNull()
//all the logic for what happens for each guess
            if (userGuess != null) {
                numberOfAttempts++
                Log.i("number of attempts", numberOfAttempts.toString())
                ViewModel.incrementAttempts()


                if (userGuess == randomNumber) {
                   Toast.makeText(context, "Congratulations! Correct guess in $numberOfAttempts attempts.", Toast.LENGTH_LONG).show()

                    val playerName = binding.playerNameEditText.text.toString()
                   viewModel.playerName = playerName
                   viewModel.score = numberOfAttempts

                    Log.i("player name", playerName)
                    Log.i("score", numberOfAttempts.toString())

                   viewModel.addGameResult()

                    val action = PlayGameDirections.actionPlayGameToMainScreen(playerName, numberOfAttempts)
                    findNavController().navigate(action)


                    ViewModel.resetAttempts()


                }
                else if(userGuess < 1 || userGuess>100) {
                    Log.i("user guess", userGuess.toString())

                    var mediaPlayer = MediaPlayer.create(context,R.raw.wrongsound)
                    mediaPlayer.start()

                   Toast.makeText(context, "Please enter a valid number!", Toast.LENGTH_SHORT).show()
                }
                else if (userGuess > randomNumber) {
                    var mediaPlayer = MediaPlayer.create(context,R.raw.wrongsound)
                    mediaPlayer.start()
                    Log.i("user guess", userGuess.toString())
                    Toast.makeText(context, "Try a smaller number!", Toast.LENGTH_SHORT).show()
                }
                else if(userGuess < randomNumber){
                    var mediaPlayer = MediaPlayer.create(context,R.raw.wrongsound)
                    mediaPlayer.start()
                    Log.i("user guess", userGuess.toString())
                   Toast.makeText(context, "Try a larger number!", Toast.LENGTH_SHORT).show()
                }

            }

        }




        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
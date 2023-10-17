package com.example.midtermproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.midtermproject.databinding.FragmentMainScreenBinding


class MainScreen : Fragment() {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        val view = binding.root




        // change screen adds name and score
        val arguments = arguments
        if (arguments?.containsKey("playerName") == true && arguments.containsKey("score") == true) {
            val playerName = arguments.getString("playerName")
            val score = arguments.getInt("score")

            // Use playerName and score safely here...
            binding.recentScore.text = "$playerName: $score"  // or format it as you wish
            binding.welcomeTextView.text = "Play again?"
        }


//play game button
        binding.playGameButton.setOnClickListener{
            val action = MainScreenDirections.actionMainScreenToPlayGame()
            view.findNavController().navigate(action)
        }


//view high scores button
        binding.viewHighScoresButton.setOnClickListener{
            val action = MainScreenDirections.actionMainScreenToViewHighScores()
            view.findNavController().navigate(action)
        }



        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
package com.example.midtermproject


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.midtermproject.databinding.FragmentViewHighScoresBinding




class ViewHighScores : Fragment() {


    private var _binding: FragmentViewHighScoresBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GameResultViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentViewHighScoresBinding.inflate(inflater, container, false)
        val view = binding.root
//set up viewmodel for each result to be displayed

        val application = requireNotNull(this.activity).application
        val dao = GameResultDatabase.getInstance(application).gameResultDao
        val viewModelFactory = GameResultViewModelFactory(dao)
        viewModel = ViewModelProvider(this, viewModelFactory).get(GameResultViewModel::class.java)



//adapter for RecyclerView
        val adapter = GameResultAdapter(emptyList()) { resultId ->
            viewModel.deleteGameResult(resultId)
        }


        binding.RecyclerView.adapter = adapter
        binding.RecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.gameResults.observe(viewLifecycleOwner) {
            it?.let {
                val sortedResults = it.sortedBy { gameResult -> gameResult.score }
                adapter.gameResults = sortedResults.toMutableList()
            }
        }




        binding.doneButton.setOnClickListener {
            findNavController().navigate(R.id.action_viewHighScores_to_mainScreen)
        }




        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

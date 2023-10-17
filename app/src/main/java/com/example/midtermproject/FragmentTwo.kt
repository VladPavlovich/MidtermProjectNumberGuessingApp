package com.example.midtermproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.midtermproject.databinding.FragmentTwoBinding


class FragmentTwo : Fragment() {

    private val viewModel: SharedViewModel by activityViewModels()

    private var _binding: FragmentTwoBinding? = null
    private val binding get() = _binding!!
//increments the number of attempts for user to see
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTwoBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel.attempts.observe(viewLifecycleOwner, { numberOfAttempts ->
            binding.attempts.text = "Number of attempts: $numberOfAttempts"
        })


        // Inflate the layout for this fragment
        return view
    }

}
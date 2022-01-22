package com.artushock.artushockenglishdictionary.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.artushock.artushockenglishdictionary.R
import com.artushock.artushockenglishdictionary.databinding.SearchFragmentBinding

class SearchFragment : Fragment(), SearchView {

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchFragmentButton.setOnClickListener {
            val word = binding.searchFragmentTextField.text.toString()
            sendRequest(word)
        }
    }

    override fun sendRequest(word: String) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_container, ResultFragment(word))
            .addToBackStack(null)
            .commit()
    }
}
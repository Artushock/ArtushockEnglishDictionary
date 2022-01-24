package com.artushock.artushockenglishdictionary.ui

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.artushock.artushockenglishdictionary.R
import com.artushock.artushockenglishdictionary.databinding.ResultFragmentBinding
import com.artushock.artushockenglishdictionary.entities.AppState
import com.artushock.artushockenglishdictionary.presenters.ResultViewModel
import com.artushock.artushockenglishdictionary.ui.model.Mapper
import com.artushock.artushockenglishdictionary.ui.model.Translation
import com.artushock.artushockenglishdictionary.ui.recycler.ResultAdapter

class ResultFragment(
    val word: String,
) : BaseFragment<AppState>() {

    private var _binding: ResultFragmentBinding? = null
    private val binding get() = _binding!!

    private var adapter: ResultAdapter? = null

    override val viewModel: ResultViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(ResultViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTranslations(word).observe(viewLifecycleOwner, {
            renderData(it)
        })
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val translations = Mapper().convertDataListToTranslationList(
                    appState.data
                )
                showResult(translations)
            }
            is AppState.Error -> {
                showError(appState.error.message.toString())
            }
            is AppState.Loading -> {
                showViewLoading()
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = ResultFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun showResult(translations: List<Translation>) {
        showViewSuccess()

        if (translations.isNullOrEmpty()) {
            showError("Error: Response from server is empty")
            return
        }

        if (adapter == null) {
            with(binding.resultFragmentRecyclerView) {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = ResultAdapter(translations)
            }
        } else {
            adapter!!.setData(translations)
        }
    }

    private fun showError(errorMessage: String) {
        showErrorView()
        binding.resultFragmentErrorText.text = errorMessage
        binding.resultFragmentErrorReloadButton.setOnClickListener(getReloadListener())
    }

    private fun getReloadListener() = OnClickListener {
        viewModel.getTranslations(word)
    }

    private fun showViewSuccess() {
        binding.resultFragmentRecyclerView.visibility = VISIBLE
        binding.resultFragmentErrorContainer.visibility = GONE
        binding.resultFragmentProgressBar.visibility = GONE

    }

    private fun showViewLoading() {
        binding.resultFragmentRecyclerView.visibility = GONE
        binding.resultFragmentErrorContainer.visibility = GONE
        binding.resultFragmentProgressBar.visibility = VISIBLE
    }

    private fun showErrorView() {
        binding.resultFragmentRecyclerView.visibility = GONE
        binding.resultFragmentErrorContainer.visibility = VISIBLE
        binding.resultFragmentProgressBar.visibility = GONE
    }
}



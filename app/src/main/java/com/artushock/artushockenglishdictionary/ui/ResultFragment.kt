package com.artushock.artushockenglishdictionary.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.artushock.artushockenglishdictionary.databinding.ResultFragmentBinding
import com.artushock.artushockenglishdictionary.entities.AppState
import com.artushock.artushockenglishdictionary.ui.model.Mapper
import com.artushock.artushockenglishdictionary.ui.model.Translation
import com.artushock.artushockenglishdictionary.ui.recycler.ResultAdapter
import com.artushock.artushockenglishdictionary.viewModels.ResultViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ResultFragment() : BaseFragment<AppState>() {

    private lateinit var word: String

    private var _binding: ResultFragmentBinding? = null
    private val binding get() = _binding!!

    private var adapter: ResultAdapter? = null

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    override lateinit var viewModel: ResultViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        word = requireArguments().getString(TRANSLATION_WORD)!!


        viewModel = viewModelFactory.create(ResultViewModel::class.java)
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

    companion object {
        private const val TRANSLATION_WORD = "TRANSLATION_WORD"

        fun newInstance(word: String): ResultFragment {
            val args = Bundle().apply {
                putString(TRANSLATION_WORD, word)
            }
            val fragment = ResultFragment()
            fragment.arguments = args

            return fragment
        }
    }
}



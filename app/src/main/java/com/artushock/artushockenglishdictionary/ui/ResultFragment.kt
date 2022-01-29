package com.artushock.artushockenglishdictionary.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.artushock.artushockenglishdictionary.databinding.ResultFragmentBinding
import com.artushock.artushockenglishdictionary.entities.AppState
import com.artushock.artushockenglishdictionary.ui.model.Mapper
import com.artushock.artushockenglishdictionary.ui.model.Translation
import com.artushock.artushockenglishdictionary.ui.recycler.ResultAdapter
import java.util.*

class ResultFragment : BaseResultFragment(), ResultView {

    private var _binding: ResultFragmentBinding? = null
    private val binding get() = _binding!!

    private var adapter: ResultAdapter? = null
    private var word: String? = null
    private var translations: List<Translation>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        word = getWordForTranslation()
        word?.let {
            presenter.getTranslations(it)
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

    private fun getWordForTranslation(): String {
        return requireArguments().getString(WORD_FOR_TRANSLATION_KEY, "")
    }

    override fun showResult(appState: AppState) {
        showViewSuccess()
        when (appState) {
            is AppState.Success -> {
                translations = Mapper().convertDataListToTranslationList(appState.data)

                if (translations.isNullOrEmpty()) {
                    showError("Error: Response from server is empty")
                } else {
                    showList(translations!!)
                }
            }
            is AppState.Error -> {
                showError(appState.error.message.toString())
            }
            is AppState.Loading -> {
                showProgress()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelableArrayList(TRANSLATION_STATE_KEY,
            translations as ArrayList<out Translation>)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        savedInstanceState?.let {
            translations = it.getParcelableArrayList(TRANSLATION_STATE_KEY)
        }
    }

    private fun showList(translations: List<Translation>) {
        if (adapter == null) {
            with(binding.resultFragmentRecyclerView) {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = ResultAdapter(translations)
            }
        } else {
            adapter!!.setData(translations)
        }
    }

    override fun showError(errorMessage: String) {
        showErrorView()
        binding.resultFragmentErrorText.text = errorMessage
        binding.resultFragmentErrorReloadButton.setOnClickListener(getReloadListener())
    }

    private fun getReloadListener() = OnClickListener {
        word?.let {
            presenter.getTranslations(it)
        }
    }


    override fun showProgress() {
        showViewLoading()
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

        private const val TRANSLATION_STATE_KEY = "TRANSLATION_STATE_KEY"
        private const val WORD_FOR_TRANSLATION_KEY = "WORD_FOR_TRANSLATION"

        fun newInstance(word: String): ResultFragment {
            val args = Bundle().apply {
                putString(WORD_FOR_TRANSLATION_KEY, word)
            }

            val fragment = ResultFragment()
            fragment.arguments = args
            return fragment
        }

    }
}
package com.artushock.artushockenglishdictionary.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.artushock.artushockenglishdictionary.databinding.ResultFragmentBinding
import com.artushock.artushockenglishdictionary.entities.AppState
import com.artushock.artushockenglishdictionary.presenters.ResultPresenter
import com.artushock.artushockenglishdictionary.presenters.ResultPresenterImpl
import com.artushock.artushockenglishdictionary.ui.model.Mapper
import com.artushock.artushockenglishdictionary.ui.recycler.ResultAdapter

class ResultFragment(
    val word: String,
) : BaseResultFragment(), ResultView {

    private var _binding: ResultFragmentBinding? = null
    private val binding get() = _binding!!

    private var adapter: ResultAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.getTranslations(word)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = ResultFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun createPresenter(): ResultPresenter<ResultView> {
        return ResultPresenterImpl()
    }

    override fun showResult(appState: AppState) {
        showViewSuccess()
        when (appState) {
            is AppState.Success -> {
                val translations = Mapper().convertDataListToTranslationList(
                    appState.data
                )

                when {
                    (translations.isNullOrEmpty()) -> {
                        showError("Error: Response from server is empty")
                    }
                    adapter == null -> {
                        with(binding.resultFragmentRecyclerView) {
                            layoutManager = LinearLayoutManager(requireContext())
                            adapter = ResultAdapter(translations)
                        }
                    }
                    else -> {
                        adapter!!.setData(translations)
                    }
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

    override fun showError(errorMessage: String) {
        showErrorView()
        binding.resultFragmentErrorText.text = errorMessage
        binding.resultFragmentErrorReloadButton.setOnClickListener(getReloadListener())
    }

    private fun getReloadListener() = OnClickListener {
        presenter.getTranslations(word)
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
}
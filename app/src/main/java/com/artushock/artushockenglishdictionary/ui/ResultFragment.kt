package com.artushock.artushockenglishdictionary.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.artushock.artushockenglishdictionary.R
import com.artushock.artushockenglishdictionary.entities.AppState
import com.artushock.artushockenglishdictionary.ui.model.Mapper
import com.artushock.artushockenglishdictionary.ui.model.Translation
import com.artushock.artushockenglishdictionary.ui.recycler.ResultAdapter
import com.artushock.artushockenglishdictionary.utils.viewById
import java.util.*

class ResultFragment : BaseResultFragment(), ResultView {

    private var adapter: ResultAdapter? = null
    private var word: String? = null
    private var translations: List<Translation>? = null

    private val recyclerView by viewById<RecyclerView>(R.id.result_fragment_recycler_view)
    private val errorContainer by viewById<LinearLayout>(R.id.result_fragment_error_container)
    private val progressBar by viewById<ProgressBar>(R.id.result_fragment_progress_bar)
    private val errorText by viewById<TextView>(R.id.result_fragment_error_text)
    private val reloadButton by viewById<Button>(R.id.result_fragment_error_reload_button)

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
        return inflater.inflate(R.layout.result_fragment, container, false)
    }

    private fun getWordForTranslation(): String {
        return requireArguments().getString(WORD_FOR_TRANSLATION_KEY, "")
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

    private fun showList(translations: List<Translation>) {
        if (adapter == null) {
            with(recyclerView) {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = ResultAdapter(translations)
            }
        } else {
            adapter!!.setData(translations)
        }
    }

    override fun showError(errorMessage: String) {
        showErrorView()
        errorText.text = errorMessage
        reloadButton.setOnClickListener(getReloadListener())
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
        recyclerView.visibility = VISIBLE
        errorContainer.visibility = GONE
        progressBar.visibility = GONE

    }

    private fun showViewLoading() {
        recyclerView.visibility = GONE
        errorContainer.visibility = GONE
        progressBar.visibility = VISIBLE
    }

    private fun showErrorView() {
        recyclerView.visibility = GONE
        errorContainer.visibility = VISIBLE
        progressBar.visibility = GONE
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
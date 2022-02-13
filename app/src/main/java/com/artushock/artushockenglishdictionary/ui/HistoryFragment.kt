package com.artushock.artushockenglishdictionary.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.artushock.artushockenglishdictionary.R
import com.artushock.artushockenglishdictionary.data.repository.local.room.HistoryEntity
import com.artushock.artushockenglishdictionary.databinding.HistoryFragmentBinding
import com.artushock.artushockenglishdictionary.ui.recycler.HistoryAdapter
import com.artushock.artushockenglishdictionary.utils.viewById

class HistoryFragment : BaseHistoryFragment(), HistoryView {

    private val progressBar by viewById<ProgressBar>(R.id.history_fragment_progress_bar)
    private val recyclerView by viewById<RecyclerView>(R.id.history_fragment_recycler_view)
    private val errorContainer by viewById<LinearLayout>(R.id.history_fragment_error_container)
    private val errorText by viewById<TextView>(R.id.history_fragment_error_text)
    private val reloadButton by viewById<Button>(R.id.history_fragment_error_reload_button)

    private var adapter: HistoryAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(R.layout.history_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.showHistory()
    }


    companion object {
        fun newInstance(): HistoryFragment {
            val args = Bundle()

            val fragment = HistoryFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun showResult(list: List<HistoryEntity>) {
        if (adapter == null) {
            with(recyclerView) {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = HistoryAdapter(list, object : HistoryAdapter.ItemClickListener {
                    override fun onHistoryItemClick(word: String) {
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.main_container, ResultFragment.newInstance(word))
                            .addToBackStack(null)
                            .commit()
                    }
                })
            }
        } else {
            adapter!!.setData(list)
        }
    }

    override fun showError(errorMessage: String) {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.GONE
        errorContainer.visibility = View.VISIBLE
        errorText.text = errorMessage
        reloadButton.setOnClickListener {
            presenter.showHistory()
        }

    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
        errorContainer.visibility = View.GONE
    }
}
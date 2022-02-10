package com.artushock.artushockenglishdictionary.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.artushock.artushockenglishdictionary.R
import com.artushock.artushockenglishdictionary.databinding.HistoryFragmentBinding
import com.artushock.artushockenglishdictionary.ui.recycler.HistoryAdapter
import com.artushock.repository.repository.local.room.HistoryEntity

class HistoryFragment : BaseHistoryFragment(), HistoryView {

    private var _binding: HistoryFragmentBinding? = null
    private val binding get() = _binding!!

    private var adapter: HistoryAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = HistoryFragmentBinding.inflate(inflater, container, false)
        return binding.root
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
            with(binding.historyFragmentRecyclerView) {
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
        binding.historyFragmentProgressBar.visibility = View.GONE
        binding.historyFragmentRecyclerView.visibility = View.GONE
        binding.historyFragmentErrorContainer.visibility = View.VISIBLE
        binding.historyFragmentErrorText.text = errorMessage
        binding.historyFragmentErrorReloadButton.setOnClickListener {
            presenter.showHistory()
        }

    }

    override fun showProgress() {
        binding.historyFragmentProgressBar.visibility = View.VISIBLE
        binding.historyFragmentRecyclerView.visibility = View.GONE
        binding.historyFragmentErrorContainer.visibility = View.GONE
    }
}
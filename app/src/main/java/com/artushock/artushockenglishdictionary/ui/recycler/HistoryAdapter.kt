package com.artushock.artushockenglishdictionary.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artushock.artushockenglishdictionary.data.repository.local.room.HistoryEntity
import com.artushock.artushockenglishdictionary.databinding.HistoryItemBinding

class HistoryAdapter(
    private var historyDataList: List<HistoryEntity>,
    private val listener: ItemClickListener,
) : RecyclerView.Adapter<HistoryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HistoryItemBinding.inflate(inflater, parent, false)
        return HistoryItemViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: HistoryItemViewHolder, position: Int) {
        holder.bind(historyDataList[position])
    }

    override fun getItemCount() = historyDataList.size

    fun setData(list: List<HistoryEntity>) {
        historyDataList = list
    }

    interface ItemClickListener {
        fun onHistoryItemClick(word: String)
    }
}
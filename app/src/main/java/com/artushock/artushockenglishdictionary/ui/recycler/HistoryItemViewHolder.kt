package com.artushock.artushockenglishdictionary.ui.recycler

import androidx.recyclerview.widget.RecyclerView
import com.artushock.artushockenglishdictionary.databinding.HistoryItemBinding
import com.artushock.repository.repository.local.room.HistoryEntity

class HistoryItemViewHolder(
    private val binding: HistoryItemBinding,
    private val listener: HistoryAdapter.ItemClickListener,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(historyEntity: HistoryEntity) {
        val word = historyEntity.word
        binding.historyResultWord.text = word
        binding.historyResultDate.text = historyEntity.date

        if (historyEntity.status) {
            binding.historyResultStatus.text = "Ok"
            binding.historyResultStatus.setBackgroundColor(0x228B22)
        } else {
            binding.historyResultStatus.text = "-"
            binding.historyResultStatus.setBackgroundColor(0xFF0000)
        }

        binding.root.setOnClickListener {
            listener.onHistoryItemClick(word)
        }
    }
}
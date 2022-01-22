package com.artushock.artushockenglishdictionary.ui.recycler

import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artushock.artushockenglishdictionary.databinding.ResultItemBinding
import com.artushock.artushockenglishdictionary.ui.model.Translation
import com.bumptech.glide.Glide
import java.io.IOException

class ResultAdapter(
    private var dataTranslationList: List<Translation>,
) : RecyclerView.Adapter<TranslationViewHolder>() {

    fun setData(dataTranslationList: List<Translation>) {
        this.dataTranslationList = dataTranslationList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranslationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ResultItemBinding.inflate(inflater, parent, false)
        return TranslationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TranslationViewHolder, position: Int) {
        holder.bind(dataTranslationList[position])
    }

    override fun getItemCount() = dataTranslationList.size

}

package com.artushock.artushockenglishdictionary.ui.recycler

import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.artushock.artushockenglishdictionary.databinding.ResultItemBinding
import com.artushock.artushockenglishdictionary.ui.model.Translation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.io.IOException

class TranslationViewHolder(private val binding: ResultItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private var context = binding.root.context

    fun bind(dataTranslation: Translation) {

        binding.resultItemTranslation.text =
            "${dataTranslation.text} | ${dataTranslation.translation}"
        binding.resultItemNote.text = if (dataTranslation.note.isNullOrEmpty()) {
            "-"
        } else {
            dataTranslation.note
        }

        Glide.with(context)
            .load("https:${dataTranslation.imageUrl}")
            .listener(getGlideListener())
            .into(binding.resultItemImage)

        binding.resultItemListenTo.setOnClickListener {
            var isPlaying = false
            val mediaPlayer = MediaPlayer()

            if (!isPlaying) {
                isPlaying = true

                try {
                    mediaPlayer.setDataSource("https:${dataTranslation.soundUrl}")
                    mediaPlayer.prepare()
                    mediaPlayer.start()
                } catch (e: IOException) {
                    Log.e("123123123", "MediaPlayer preparing is failed")
                }
            } else {
                isPlaying = false
                mediaPlayer.release()
            }
        }
    }

    private fun getGlideListener() = object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean,
        ): Boolean {
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean,
        ): Boolean {
            binding.resultItemImageProgressBar.visibility = View.GONE
            return false
        }

    }
}

package com.example.ramtt.ui.fragment.episode.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ramtt.data.dto.episodemodel.EpisodeResult
import com.example.ramtt.databinding.ItemEpisodeBinding

/**
 * @author : Mingaleev D
 * @data : 8/01/2023
 */

class EpisodeAdapter : RecyclerView.Adapter<EpisodeAdapter.MyViewHolder>() {

   inner class MyViewHolder(val binding: ItemEpisodeBinding) :
      RecyclerView.ViewHolder(binding.root) {
      @SuppressLint("SetTextI18n")
      fun bind(episode: EpisodeResult) {
         binding.apply {
            episodeNameTv.text = episode.name
            episodeAirDateTv.text = "air date: " + episode.airDate

            episodeSeasonTv.text = "season: " + episode.episode.substring(4)
            episodeEpisodeTv.text = "episode: " + episode.episode.substring(1, 3)

         }
      }

   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val binding = ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      return MyViewHolder(binding)
   }

   override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      val episodeResult = differ.currentList[position]
      holder.bind(episodeResult)
   }

   override fun getItemCount(): Int {
      return differ.currentList.size
   }
   private val callback = object : DiffUtil.ItemCallback<EpisodeResult>() {
      override fun areItemsTheSame(
         oldItem: EpisodeResult,
         newItem: EpisodeResult
      ): Boolean {
         return oldItem.id == newItem.id
      }

      override fun areContentsTheSame(
         oldItem: EpisodeResult,
         newItem: EpisodeResult
      ): Boolean {
         return oldItem == newItem
      }
   }
   val differ = AsyncListDiffer(this, callback)
}
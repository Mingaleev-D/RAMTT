package com.example.ramtt.ui.fragment.location.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ramtt.common.load
import com.example.ramtt.data.dto.charactermodel.CharacterResult
import com.example.ramtt.data.dto.locationmodel.LocationResult
import com.example.ramtt.databinding.ItemCharacterBinding
import com.example.ramtt.databinding.ItemLocationBinding
import com.example.ramtt.ui.fragment.character.adapter.CharacterAdapter

/**
 * @author : Mingaleev D
 * @data : 8/01/2023
 */

class LocationAdapter : RecyclerView.Adapter<LocationAdapter.MyViewHolder>() {

   inner class MyViewHolder(val binding: ItemLocationBinding) :
      RecyclerView.ViewHolder(binding.root) {
      @SuppressLint("SetTextI18n")
      fun bind(location: LocationResult) {
         binding.apply {
            locationNameTv.text = location.name
            locationTypeTv.text = "type: " + location.type
            locationDimensionTv.text = "dimension: " + location.dimension

         }
      }

   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val binding = ItemLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      return MyViewHolder(binding)
   }

   override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      val locationResult = differ.currentList[position]
      holder.bind(locationResult)
   }

   override fun getItemCount(): Int {
      return differ.currentList.size
   }
   private val callback = object : DiffUtil.ItemCallback<LocationResult>() {
      override fun areItemsTheSame(
         oldItem: LocationResult,
         newItem: LocationResult
      ): Boolean {
         return oldItem.id == newItem.id
      }

      override fun areContentsTheSame(
         oldItem: LocationResult,
         newItem: LocationResult
      ): Boolean {
         return oldItem == newItem
      }
   }
   val differ = AsyncListDiffer(this, callback)
}
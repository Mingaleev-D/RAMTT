package com.example.ramtt.ui.fragment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ramtt.common.load
import com.example.ramtt.data.dto.charactermodel.CharacterResult
import com.example.ramtt.databinding.ItemCharacterBinding

/**
 * @author : Mingaleev D
 * @data : 8/01/2023
 */

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.MyViewHolder>() {

   inner class MyViewHolder(val binding: ItemCharacterBinding) :
      RecyclerView.ViewHolder(binding.root) {
      @SuppressLint("SetTextI18n")
      fun bind(character: CharacterResult) {
         binding.apply {
            nameTv.text = character.name
            genderTv.text = "gender: " + character.gender
            speciesTv.text = "species: " + character.species
            locationTv.text = "location: " + character.location.name

            imageCharacter.load(character.image)
         }
      }

   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      return MyViewHolder(binding)
   }

   override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      val characterResult = differ.currentList[position]
      holder.bind(characterResult)
   }

   override fun getItemCount(): Int {
      return differ.currentList.size
   }
   private val callback = object : DiffUtil.ItemCallback<CharacterResult>() {
      override fun areItemsTheSame(
         oldItem: CharacterResult,
         newItem: CharacterResult
      ): Boolean {
         return oldItem.id == newItem.id
      }

      override fun areContentsTheSame(
         oldItem: CharacterResult,
         newItem: CharacterResult
      ): Boolean {
         return oldItem == newItem
      }
   }
   val differ = AsyncListDiffer(this, callback)
}
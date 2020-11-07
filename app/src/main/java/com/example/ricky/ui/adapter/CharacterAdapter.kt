package com.example.ricky.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.ricky.R
import com.example.ricky.data.db.model.Character
import com.example.ricky.ui.fragment.CharacterDetail
import com.example.ricky.utils.CharacterItemClickListener
import kotlinx.android.synthetic.main.item_character.view.*


class CharacterAdapter(
    private var characters: List<Character>,
    private val characterItemClickListener: CharacterItemClickListener
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CharacterViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
    )

    override fun getItemCount() = characters.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]

        holder.itemView.apply {
            textViewCharacterName.text = character.name
            imageViewCharacterImage.load(character.image)

            setOnClickListener {
                characterItemClickListener.onItemClick(character)
            }
        }

    }

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
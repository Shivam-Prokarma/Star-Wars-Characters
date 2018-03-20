package com.shivam.marvelgallery.data.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.shivam.marvelgallery.R
import com.shivam.marvelgallery.data.model.StarWarsCharacter

/**
 * Created by stomar on 3/18/18.
 */
class MarvelCharactersAdapter(var mCharacterList: List<StarWarsCharacter>) : RecyclerView.Adapter<MarvelCharactersAdapter.StarWarCharactersViewHolder>() {

    interface CharacterItemClickListener {
        fun onClick(i: Int)
    }

    private lateinit var mContext: Context

    lateinit var clickListener: CharacterItemClickListener

    constructor(context: Context, characterList: List<StarWarsCharacter>, clickListener: CharacterItemClickListener) : this(characterList) {
        mContext = context
        this.clickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): StarWarCharactersViewHolder? {
        var listItemView = LayoutInflater.from(parent?.context).inflate(R.layout.character_list_item, parent, false)
        return StarWarCharactersViewHolder(listItemView)
    }

    override fun getItemCount(): Int {
        return mCharacterList.size
    }

    override fun onBindViewHolder(holder: StarWarCharactersViewHolder?, position: Int) {
        holder?.updateData()
    }

    inner class StarWarCharactersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var mTextViewName = view.findViewById<TextView>(R.id.name)
        private var mTextViewHeight: TextView = view.findViewById<TextView>(R.id.height)

        init {
            view.setOnClickListener {
                clickListener.onClick(adapterPosition)
            }
        }

        fun updateData() {
            var starWarsCharacter = mCharacterList[adapterPosition]
            mTextViewName.text = String.format(mTextViewName.resources.getString(R.string.name), starWarsCharacter.name)
            mTextViewHeight.text = String.format(mTextViewName.resources.getString(R.string.height), starWarsCharacter.height)
        }
    }
}
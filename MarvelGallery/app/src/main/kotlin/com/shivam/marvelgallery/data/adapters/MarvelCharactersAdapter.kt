package com.shivam.marvelgallery.data.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.shivam.marvelgallery.R
import com.shivam.marvelgallery.data.model.StarWarsCharacterComplete

/**
 * Created by stomar on 3/18/18.
 */
class MarvelCharactersAdapter(var mCharacterList: MutableList<StarWarsCharacterComplete>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var isLoaderAdded: Boolean = false

    companion object {
        const val ITEM_TYPE_CHARACTER_ITEM = 0
        const val ITEM_TYPE_SPINNER_ITEM = 1
    }

    interface CharacterItemClickListener {
        fun onClick(i: Int)
    }

    private lateinit var mContext: Context

    lateinit var clickListener: CharacterItemClickListener

    constructor(context: Context, characterList: List<StarWarsCharacterComplete>, clickListener: CharacterItemClickListener) : this(characterList.toMutableList()) {
        mContext = context
        this.clickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_TYPE_CHARACTER_ITEM -> {
                var listItemView = LayoutInflater.from(parent?.context).inflate(R.layout.character_list_item, parent, false)
                StarWarCharactersViewHolder(listItemView)
            }
            else -> {
                ProgressBarViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.recycler_view_loader, parent, false))
            }
        }
    }

    override fun getItemCount(): Int {
        return mCharacterList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ITEM_TYPE_CHARACTER_ITEM) {
            (holder as? StarWarCharactersViewHolder)?.updateData()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == mCharacterList.size - 1 && isLoaderAdded) ITEM_TYPE_SPINNER_ITEM else ITEM_TYPE_CHARACTER_ITEM
    }

    inner class StarWarCharactersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var mTextViewName = view.findViewById<TextView>(R.id.name)
        private var mTextViewHeight: TextView = view.findViewById<TextView>(R.id.height)
        private var mTextViewMass = view.findViewById<TextView>(R.id.mass)
        private var mTextViewHairColor: TextView = view.findViewById<TextView>(R.id.hair_color)
        private var mTextViewSkinColor = view.findViewById<TextView>(R.id.skin_color)
        private var mTextViewEyeColor: TextView = view.findViewById<TextView>(R.id.eye_color)
        private var mTextViewBirthYear = view.findViewById<TextView>(R.id.birth_year)
        private var mTextViewGender: TextView = view.findViewById<TextView>(R.id.gender)

        init {
            view.setOnClickListener {
                clickListener.onClick(adapterPosition + 1)
            }
        }

        fun updateData() {
            var starWarsCharacter = mCharacterList[adapterPosition]
            mTextViewName.text = String.format(mTextViewName.resources.getString(R.string.name), starWarsCharacter.name)
            mTextViewHeight.text = String.format(mTextViewName.resources.getString(R.string.height), starWarsCharacter.height)
            mTextViewMass.text = String.format(mTextViewMass.resources.getString(R.string.mass), starWarsCharacter.mass)
            mTextViewHairColor.text = String.format(mTextViewHairColor.resources.getString(R.string.hair_color), starWarsCharacter.hairColor)
            mTextViewSkinColor.text = String.format(mTextViewSkinColor.resources.getString(R.string.skin_color), starWarsCharacter.skinColor)
            mTextViewEyeColor.text = String.format(mTextViewEyeColor.resources.getString(R.string.eye_color), starWarsCharacter.eyeColor)
            mTextViewBirthYear.text = String.format(mTextViewBirthYear.resources.getString(R.string.birth_year), starWarsCharacter.birthYear)
            mTextViewGender.text = String.format(mTextViewGender.resources.getString(R.string.gender), starWarsCharacter.gender)
        }
    }

    class ProgressBarViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    fun addLoader() {
        isLoaderAdded = true
        mCharacterList.add(StarWarsCharacterComplete())
        notifyItemInserted(mCharacterList.size - 1)
    }

    fun removeLoader() {
        if (!isLoaderAdded) {
            return
        }
        isLoaderAdded = false
        mCharacterList.removeAt(mCharacterList.size - 1)
        notifyItemRemoved(mCharacterList.size - 1)
    }

    fun addMoreCharacters(listCharacter: List<StarWarsCharacterComplete>) {
        mCharacterList.addAll(listCharacter)
        notifyItemRangeInserted(mCharacterList.size - listCharacter.size, listCharacter.size)
    }
}
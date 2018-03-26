package com.shivam.marvelgallery.data.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.shivam.marvelgallery.R
import com.shivam.marvelgallery.data.model.StarWarsFilm

/**
 * Created by stomar on 3/25/18.
 */
class StarWarFilmsAdapter(var starWarFilms: List<StarWarsFilm>) : RecyclerView.Adapter<StarWarFilmsAdapter.StarWarFilmViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): StarWarFilmViewHolder{
        var lisItemView = LayoutInflater.from(parent?.context).inflate(R.layout.starwars_film_item, parent, false)
        return StarWarFilmViewHolder(lisItemView)
    }

    override fun getItemCount(): Int = starWarFilms.size

    override fun onBindViewHolder(holder: StarWarFilmViewHolder?, position: Int) {
        holder?.updateData()
    }

    inner class StarWarFilmViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById<TextView>(R.id.textview_film_title)
        var episodeId: TextView = view.findViewById<TextView>(R.id.textview_film_episode)
        var director: TextView = view.findViewById<TextView>(R.id.textview_film_director)
        var producer: TextView = view.findViewById<TextView>(R.id.textview_film_producer)
        var releaseDate: TextView = view.findViewById<TextView>(R.id.textview_film_releasedate)

        fun updateData() {
            title.text = String.format(title.context.getString(R.string.film_title), starWarFilms[adapterPosition].title)
            episodeId.text = String.format(title.context.getString(R.string.film_episode), starWarFilms[adapterPosition].episodeId)
            director.text = String.format(title.context.getString(R.string.film_director), starWarFilms[adapterPosition].director)
            producer.text = String.format(title.context.getString(R.string.film_producer), starWarFilms[adapterPosition].producer)
            releaseDate.text = String.format(title.context.getString(R.string.film_release_date), starWarFilms[adapterPosition].releaseDate)
        }
    }
}
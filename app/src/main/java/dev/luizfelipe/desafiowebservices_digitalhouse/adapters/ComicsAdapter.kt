package dev.luizfelipe.desafiowebservices_digitalhouse.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.luizfelipe.desafiowebservices_digitalhouse.R
import dev.luizfelipe.desafiowebservices_digitalhouse.interfaces.ComicClick
import dev.luizfelipe.desafiowebservices_digitalhouse.models.Result

class ComicsAdapter(private val context: Context, private val listComic: MutableList<Result>, private val listener : ComicClick) : RecyclerView.Adapter<ComicsAdapter.ComicsViewHolder>() {

    inner class ComicsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageComic: ImageView = itemView.findViewById(R.id.image_comic)
        val titleComic: TextView = itemView.findViewById(R.id.title_comic)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.item_comic, parent, false)
        return ComicsViewHolder(root)
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        holder.titleComic.text = "#${listComic[position].id}"
        if (listComic[position].images.isNotEmpty()) {
            Glide
                .with(context)
                .load("${listComic[position].images[0].path}.${listComic[position].images[0].extension}")
                .into(holder.imageComic)
        }
        holder.itemView.setOnClickListener(listener.onComicClickListener(listComic[position]))
    }

    override fun getItemCount(): Int = listComic.size

    fun add(vararg comics: Result) {
        listComic.addAll(comics)
    }

    fun add(comics: List<Result>){
        listComic.addAll(comics)
        notifyDataSetChanged()
    }
}
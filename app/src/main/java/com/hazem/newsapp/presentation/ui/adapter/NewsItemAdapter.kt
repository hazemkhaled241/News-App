package com.hazem.newsapp.presentation.ui.adapter

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.hazem.newsapp.data.model.Articles
import com.hazem.newsapp.R
import com.squareup.picasso.Picasso

class NewsItemAdapter( var items: List<Articles>) :
    RecyclerView.Adapter<NewsItemAdapter.NewsViewHolder>() {
    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var photo: ImageView? = null
        var textView: TextView? = null
        var cardView:CardView?=null

        init {
            photo = view.findViewById(R.id.iv_news_image)
            textView = view.findViewById(R.id.tv_news_title)
            cardView=view.findViewById(R.id.cv_news)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        var articals: Articles = items[position]
        holder.textView?.text = articals.title
        if(articals.urlToImage!=null)
          Picasso.get().load(articals.urlToImage).into(holder.photo)
        else
            holder.photo!!.setBackgroundResource(R.drawable.noimage)

        holder.cardView?.setOnClickListener {
        val uri:Uri=Uri.parse(articals.url)
        val openUrl=Intent(android.content.Intent.ACTION_VIEW)
            openUrl.data=uri
            it.context.startActivity(openUrl)
        }
        Log.d("NewsAdapter", "onBindViewHolder: ${articals.urlToImage}")

    }

    override fun getItemCount(): Int {
        return items.size
    }

}
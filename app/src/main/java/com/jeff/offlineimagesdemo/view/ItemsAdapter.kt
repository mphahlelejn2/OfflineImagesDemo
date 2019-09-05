package com.jeff.offlineimagesdemo.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.jeff.offlineimagesdemo.model.Item
import kotlinx.android.synthetic.main.news_list_item.view.*
import kotlinx.android.synthetic.main.news_list_item.view.imageView
import com.jeff.offlineimagesdemo.util.Utils


class ItemsAdapter(private var itemList:List<Item>, private val context: Context): RecyclerView.Adapter<ItemsAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ArticleViewHolder {
        return ArticleViewHolder(LayoutInflater.from(context).inflate(com.jeff.offlineimagesdemo.R.layout.news_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, int: Int) {
        val item = itemList[int]
        holder.title.text= item.title
        holder.description.text= item.description
        Utils.loadImage(context, holder.image, item.url)
        holder.link.setOnClickListener { startDetailedActivity(item) }
    }

    private fun startDetailedActivity(item: Item) {
        var intent=Intent(context,DetailsActivity::class.java)
        intent.putExtra("id", item.imageId)
        context.startActivity(intent)
    }

    override fun getItemCount()=itemList.size

    fun updateRecyclerViewData(itemList: List<Item>) {
        this.itemList=itemList
         notifyDataSetChanged()
    }

    class ArticleViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val title: TextView=view.tvTitle
        val image: ImageView= view.imageView
        val link:TextView=view.tvLink
        val description:TextView=view.tvDescription
    }
}
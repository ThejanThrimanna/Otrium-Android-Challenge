package com.thejan.otrium_android.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.thejan.otrium_android.R
import com.thejan.otrium_android.database.entities.RepositoryTable
import kotlinx.android.synthetic.main.layout_repo_item.view.*

class RepositoryAdapter(private val repos: List<RepositoryTable>) : RecyclerView.Adapter<RepositoryAdapter.ItemViewHolder>() {

    override fun getItemCount(): Int = repos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val v = inflater.inflate(R.layout.layout_repo_item, parent, false)
        return ItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val adapterItem = repos[position]
        Picasso.get()
            .load(adapterItem.image)
            .into(holder.circleImageView)

        holder.tvCompanyName.text = adapterItem.companyName
        holder.tvDesc.text = adapterItem.description
        holder.tvLanguage.text = adapterItem.languages
        holder.tvStars.text = adapterItem.stars.toString()
        holder.tvName.text = adapterItem.name
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val circleImageView = itemView.circleImageView
        val tvCompanyName = itemView.tvCompanyName
        val tvName = itemView.tvName
        val tvDesc = itemView.tvDesc
        val tvStars = itemView.tvStars
        val tvLanguage = itemView.tvLanguage
        init {
            @BindingAdapter("profileImage")
            fun setImageUrl(view: ImageView, imageUrl: String?) {
                Picasso.get()
                    .load(imageUrl)
                    .into(view)
            }
        }
    }
}

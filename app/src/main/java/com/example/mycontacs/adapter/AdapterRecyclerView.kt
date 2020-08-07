package com.example.mycontacs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycontacs.R
import com.example.mycontacs.model.ModelContactsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.style_list_contacts.view.*

class AdapterRecyclerView(
    private var list: ArrayList<ModelContactsItem>,
    private val listener: OnClickSelectedItem

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var originalList: ArrayList<ModelContactsItem> = arrayListOf()
    private var pos = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.style_list_contacts, parent, false)
        return AdapterViewHolder(view)
    }

    fun addData(data: ArrayList<ModelContactsItem>) {
        list.addAll(data)
        originalList.addAll(list)
        notifyDataSetChanged()
    }

    fun getPosition(): Int {
        return pos
    }

    fun getOriginalList(): ArrayList<ModelContactsItem> {
        return originalList
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AdapterViewHolder) {
            pos = position
            holder.bind(list[position])
        }
    }

    interface OnClickSelectedItem {
        fun onClick(pos: Int)
    }

    inner class AdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        fun bind(contacts: ModelContactsItem) {
            itemView.apply {
                nameListContact.text = contacts.name
                descriptionContact.text = contacts.companyName
                if(contacts.isFavorite) {
                    imageViewFavorite
                        .setImageDrawable(resources.getDrawable(R.drawable.image_favorite_true))
                }
                else imageViewFavorite
                    .setImageDrawable(resources.getDrawable(R.drawable.image_favorite_false))

                if (!contacts.smallImageURL.isNullOrBlank()) {
                    Picasso.with(itemView.context).load(contacts.smallImageURL)
                        .placeholder(R.drawable.user_image_small)
                        .resize(220, 220)
                        .centerCrop()
                        .into(imageViewListContacts)
                }
            }
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            listener.onClick(position)
        }
    }
}

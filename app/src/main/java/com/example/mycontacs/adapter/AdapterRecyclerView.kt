package com.example.mycontacs.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycontacs.R
import com.example.mycontacs.adapter.modelAdapter.Header
import com.example.mycontacs.data.model.ModelContactsItem
import com.example.mycontacs.utils.Category
import com.example.mycontacs.utils.hide
import com.example.mycontacs.utils.show
import com.google.android.material.shape.CornerFamily
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.header_recyclerview.view.*
import kotlinx.android.synthetic.main.style_list_contacts.view.*
import kotlin.collections.ArrayList


class AdapterRecyclerView(private var list: ArrayList<Any>,
                          private val listener: OnClickSelectedItem)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var originalList: ArrayList<Any> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =

        when (viewType) {
            Category.TYPE_ITEM -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.style_list_contacts, parent, false)
                view.imageViewListContacts.shapeAppearanceModel = view.imageViewListContacts.shapeAppearanceModel
                    .toBuilder()
                    .setTopLeftCorner(CornerFamily.ROUNDED, 50F)
                    .setBottomRightCorner(CornerFamily.ROUNDED, 50F)
                    .build()
                ItemViewHolder(view)
            }
            Category.TYPE_HEADER -> {
               HeaderViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.header_recyclerview, parent, false)
                )
            } else -> throw ClassCastException("Unknown viewType $viewType")
        }

    fun addData(data: ArrayList<*>) {
        list.addAll(data)
        originalList.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            Category.TYPE_ITEM -> {
                val currentItem = holder as ItemViewHolder
                currentItem.bind(list[position] as ModelContactsItem)
            }
            Category.TYPE_HEADER -> {
                val currentItem = holder as HeaderViewHolder
                currentItem.bind(list[position] as Header)

            }
            else -> throw ClassCastException("Unknown ViewType ${holder.itemViewType}")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isPositionHeader(position)) Category.TYPE_HEADER else Category.TYPE_ITEM
    }

    private fun isPositionHeader(position: Int): Boolean {
        return list[position] is Header
    }

    interface OnClickSelectedItem {
        fun onClick(pos: Int)
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        fun bind(contacts: ModelContactsItem) {

            itemView.apply {
                nameListContact.text = contacts.name
                descriptionContact.text = contacts.companyName
                if (contacts.isFavorite) imageViewFavorite.show()
                else imageViewFavorite.hide()

                if (!contacts.smallImageURL.isNullOrBlank()) {
                    Picasso.with(itemView.context).load(contacts.smallImageURL)
                        .placeholder(R.drawable.user_image_small)
                        .resize(225, 225)
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

    inner class HeaderViewHolder(headerView: View) : RecyclerView.ViewHolder(headerView) {
        fun bind(header: Header) {
            itemView.othersContactsTitle.text = itemView.resources.getString(header.CategoryContact)
        }
    }
}
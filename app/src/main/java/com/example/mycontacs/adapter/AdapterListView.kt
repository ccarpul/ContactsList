package com.example.mycontacs.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.fragment.app.FragmentActivity
import com.example.mycontacs.R
import com.example.mycontacs.adapter.modelAdapter.ItemDetailModel
import com.example.mycontacs.adapter.modelAdapter.ItemDetailViewHolder
import com.google.android.material.shape.CornerFamily
import kotlinx.android.synthetic.main.style_list_contacts.view.*
import kotlin.collections.ArrayList

class AdapterListView(
    private val activity: FragmentActivity?,
    private val items: ArrayList<ItemDetailModel?>
) : BaseAdapter() {

lateinit var holder: ItemDetailViewHolder
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        var v = convertView

        if (convertView == null) {

            val inf =
                activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            v = inf.inflate(R.layout.style_item_detail, null)

            holder =
                ItemDetailViewHolder(
                    v?.findViewById(R.id.titleItemDetail),
                    v?.findViewById(R.id.valueItem),
                    v?.findViewById(R.id.typeItem)
                )
            v.tag = holder

        } else {
            holder = v?.tag as ItemDetailViewHolder
        }

        val contactItem = items[position]
        holder.titulo?.text = contactItem?.titulo
        holder.value?.text = contactItem?.value
        holder.type?.text = contactItem?.type

        return v
    }

    override fun getItem(position: Int): ItemDetailModel? {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }
}
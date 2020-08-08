package com.example.mycontacs.utils

import android.util.Log
import com.example.mycontacs.R
import com.example.mycontacs.adapter.Header
import com.example.mycontacs.data.model.ModelContactsItem

fun customSort(list: List<ModelContactsItem>): List<Any> {
    var sorted = list
    sorted = sorted.sortedWith(compareBy { it.name })
    sorted = sorted.sortedWith(compareByDescending { it.isFavorite })
    val otherContacts = arrayListOf<ModelContactsItem>()
    val favoriteContacts = arrayListOf<ModelContactsItem>()
    sorted.forEach {
        if (!it.isFavorite) {
            otherContacts.add(it)
        } else {
            favoriteContacts.add(it)
        }
    }

    val listFinal = arrayListOf<Any>()
    listFinal.add(Header(R.string.title_list_Favorites))
    listFinal.addAll(favoriteContacts)
    listFinal.add(Header(R.string.title_list_Others))
    listFinal.addAll(otherContacts)
    return listFinal
}


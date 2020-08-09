package com.example.mycontacs.utils

import com.example.mycontacs.R
import com.example.mycontacs.adapter.modelAdapter.Header
import com.example.mycontacs.data.model.ModelContactsItem

fun ArrayList<ModelContactsItem>.customSort(): ArrayList<*> {

    val sortedList =
        this.sortedWith(compareBy { it.name })
        .sortedWith(compareByDescending { it.isFavorite })

    return sortedList.addHeader()
}

fun List<ModelContactsItem>.addHeader(): ArrayList<Any>{

    val otherContacts = arrayListOf<Any>(
        Header(
            R.string.title_list_Others
        )
    )
    val favoriteContacts = arrayListOf<Any>(
        Header(
            R.string.title_list_Favorites
        )
    )

    this.forEach {
        if (it.isFavorite) favoriteContacts.add(it)
        else otherContacts.add(it)
    }

    favoriteContacts.addAll(otherContacts)

    return favoriteContacts
}


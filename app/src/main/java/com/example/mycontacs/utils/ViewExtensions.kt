package com.example.mycontacs.utils

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.mycontacs.R

fun View.hide() { isVisible = false }

fun View.show() {isVisible = true }

fun TextView.setImage(drawable: Int) {
    this.setCompoundDrawablesWithIntrinsicBounds(
        drawable, 0, 0, 0
    )
}

fun TextView.setupToolbar(title: String, color: Int, aligment: Int) {
    this.apply {
        text = title
        setTextColor(color)
        textAlignment = aligment
    }
}

fun ImageView.setIconCategoryContact(favorite: Boolean){
    if (favorite) this.setBackgroundResource(R.drawable.image_favorite_true)
    else this.setBackgroundResource(R.drawable.image_favorite_false)
}


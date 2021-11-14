package com.dubizzle.assignment.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.dubizzle.assignment.R

fun ImageView.setImageUrl(url: String) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_placeholder)
        .into(this)
}

fun Activity.showToast(message: String, toastLength: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, toastLength)
        .show()
}

inline fun <reified T : Any> Activity.launchActivity(
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivity(intent, options)
}


inline fun <reified T : Any> newIntent(context: Context): Intent = Intent(context, T::class.java)

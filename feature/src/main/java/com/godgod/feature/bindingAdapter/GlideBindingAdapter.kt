package com.godgod.feature.bindingAdapter

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.godgod.feature.GlideApp
import com.godgod.feature.R

@BindingAdapter("bind:imageUri")
fun ImageView.setImageUri(uri: Uri) {
    GlideApp.with(context)
        .asBitmap()
        .load(uri)
        .centerCrop()
        .placeholder(R.drawable.ic_launcher_background)
        .into(this)
}
package com.example.booklistapplication.presentation.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.FitCenter
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

object AdapterBindings {

    @JvmStatic
    @BindingAdapter("loadImage", "radius", "cornerType", requireAll = false)
    fun loadImageWithRadius(
        view: ImageView, url: String?,
        radius: Int?,
        cornerType: RoundedCornersTransformation.CornerType?
    ) {
        var radiusPx = 0
        val multiTransformation = if (cornerType != null && radius != null) {
            val scale = view.context.resources.displayMetrics.density
            radiusPx = (radius * scale + 0.5f).toInt()

            MultiTransformation(
                FitCenter(),gitg
                RoundedCornersTransformation(
                    radiusPx, 0,
                    cornerType
                )
            )
        } else {
            MultiTransformation(FitCenter())
        }

        Glide.with(view).load(url).transform(multiTransformation).into(view)
    }

}
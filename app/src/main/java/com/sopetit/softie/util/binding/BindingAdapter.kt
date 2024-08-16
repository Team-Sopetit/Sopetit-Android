package com.sopetit.softie.util.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.decode.SvgDecoder
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.bumptech.glide.Glide
import com.sopetit.softie.R

object BindingAdapter {
    @BindingAdapter("setImage")
    @JvmStatic
    fun ImageView.setImage(imgUrl: String?) {
        this.let {
            Glide.with(context).load(imgUrl).into(this)
        }
    }

    @BindingAdapter("setCoilImage")
    @JvmStatic
    fun ImageView.setCoilImage(imgUrl: String?) {
        this.let {
            it.load(imgUrl) {
                if (imgUrl?.endsWith(".svg") == true) {
                    decoderFactory { result, options, _ -> SvgDecoder(result.source, options) }
                }
                crossfade(true)
            }
        }
    }

    @BindingAdapter("setHappyCoilImage")
    @JvmStatic
    fun ImageView.setHappyCoilImage(imgUrl: String?) {
        this.let {
            it.load(imgUrl) {
                if (imgUrl?.endsWith(".svg") == true) {
                    decoderFactory { result, options, _ -> SvgDecoder(result.source, options) }
                }
                crossfade(true)
                placeholder(R.drawable.ic_happy_card_base)
                error(R.drawable.ic_happy_card_base)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("setCircleImage")
    fun ImageView.setCircleImage(imgUrl: String?) {
        this.let {
            val request = ImageRequest.Builder(context)
                .data(imgUrl)
                .target(this)
                .transformations(CircleCropTransformation())
                .fallback(R.drawable.ic_happy_card_base)
                .build()
            context.imageLoader.enqueue(request)
        }
    }
}

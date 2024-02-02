package com.sopetit.softie.util.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.decode.SvgDecoder
import coil.load
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

    @BindingAdapter("setBaseCoilImage")
    @JvmStatic
    fun ImageView.setBaseCoilImage(imgUrl: String?) {
        this.let {
            it.load(imgUrl) {
                if (imgUrl?.endsWith(".svg") == true) {
                    decoderFactory { result, options, _ -> SvgDecoder(result.source, options) }
                }
                crossfade(true)
                placeholder(R.drawable.ic_loading_bear)
                error(R.drawable.ic_loading_bear)
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
}

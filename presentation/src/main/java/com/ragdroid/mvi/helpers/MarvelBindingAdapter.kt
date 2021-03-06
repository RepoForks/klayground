package com.ragdroid.mvi.helpers

import android.databinding.BindingConversion
import android.graphics.drawable.Drawable
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by garimajain on 19/11/17.
 */
@Singleton
class MarvelBindingAdapter @Inject
constructor(val requestManager: RequestManager) {


    /**
     * Bind Glide with an ImageView.
     * https://medium.com/fueled-android/data-binding-adapter-write-bind-repeat-50e9c64fe806
     *
     * @param view the ImageView to bind to Glide.
     * @param src The URL of the image to load.
     * @param placeholder The placeholder icon.
     * @param error The error icon.
     * @param blurValue The blur radius value between 1 and 25.
     * @param cropCircle Crop the image in a circle of not.
     */
    @android.databinding.BindingAdapter(
            value = ["src", "placeholder", "error", "blur", "cropCircle", "centerCrop"],
            requireAll = false)
    fun setImageUrl(view: ImageView, src: String, placeholder: Drawable?, error: Drawable?,
                    blurValue: Int, cropCircle: Boolean, centerCrop: Boolean) {

        val glideBuilder = requestManager.load(src)

        val requestOptions = RequestOptions()

        placeholder?.let { requestOptions.placeholder(placeholder) }

        requestOptions.apply {

            if (cropCircle) {
                circleCrop()
            }

            if (centerCrop) {
                centerCrop()
            }

            if (error != null) {
                error(error)
            }
        }

        glideBuilder.apply(requestOptions)

        glideBuilder.into(view)
    }



    @android.databinding.BindingAdapter(value = ["refreshing"])
    fun setPulltoRefreshing(swipeRefreshLayout: SwipeRefreshLayout, refreshing: Boolean) {
        swipeRefreshLayout.isRefreshing = refreshing
        swipeRefreshLayout.isEnabled = true
    }

}


class MarvelBindingComponent
@Inject constructor(val adapter: MarvelBindingAdapter):
        android.databinding.DataBindingComponent {

    override fun getMarvelBindingAdapter(): MarvelBindingAdapter = adapter

}

@BindingConversion
fun convertBooleanToVisibility(visible: Boolean): Int = when(visible) {
    true -> View.VISIBLE
    false -> View.GONE
}

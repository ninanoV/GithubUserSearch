package com.ninanok.githubusersearch.rest.response.data

import android.view.View

open class BindingUtil {

    fun nullValueVisibilityIsGone(value: Any?): Int {
        return if(value != null) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}
package com.rhodar.mobile.codescrum.viewmodel.util

import android.databinding.BindingAdapter
import android.widget.TextView


class BindingUtil{
    companion object {
        @JvmStatic
        @BindingAdapter("app:longToText")
        fun longToText(view: TextView, long: Long){
            view.text = long.toString()
        }
    }
}
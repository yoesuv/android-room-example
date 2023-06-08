package com.yoesuv.androidroom.utils

import android.content.Context

class Utility {

    companion object {

        fun getToolbarHeight(context: Context): Int {
            val styledAttributes = context.theme.obtainStyledAttributes(intArrayOf(com.google.android.material.R.attr.actionBarSize))
            val toolbarHeight = styledAttributes.getDimension(0, 0f).toInt()
            styledAttributes.recycle()

            return toolbarHeight
        }
    }

}
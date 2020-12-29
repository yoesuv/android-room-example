package com.yoesuv.androidroom.utils

import android.content.Context
import com.yoesuv.androidroom.R

class Utility {

    companion object {

        fun getToolbarHeight(context: Context): Int {
            val styledAttributes = context.theme.obtainStyledAttributes(intArrayOf(R.attr.actionBarSize))
            val toolbarHeight = styledAttributes.getDimension(0, 0f).toInt()
            styledAttributes.recycle()

            return toolbarHeight
        }
    }

}
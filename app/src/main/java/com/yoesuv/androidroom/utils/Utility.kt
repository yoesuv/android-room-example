package com.yoesuv.androidroom.utils

import android.app.Activity
import android.content.Context
import android.graphics.Point
import com.yoesuv.androidroom.R

class Utility {

    companion object {
        fun getScreenSize(activity: Activity): Point{
            val display = activity.windowManager.defaultDisplay
            val size = Point()
            display.getSize(size)
            return size
        }

        fun getToolbarHeight(context: Context): Int {
            val styledAttributes = context.theme.obtainStyledAttributes(
                    intArrayOf(R.attr.actionBarSize))
            val toolbarHeight = styledAttributes.getDimension(0, 0f).toInt()
            styledAttributes.recycle()

            return toolbarHeight
        }
    }

}
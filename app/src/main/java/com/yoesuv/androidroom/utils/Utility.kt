package com.yoesuv.androidroom.utils

import android.app.Activity
import android.graphics.Point

class Utility {

    companion object {
        fun getScreenSize(activity: Activity): Point{
            val display = activity.windowManager.defaultDisplay
            val size = Point()
            display.getSize(size)
            return size
        }
    }

}
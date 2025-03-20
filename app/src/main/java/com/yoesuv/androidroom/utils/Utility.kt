package com.yoesuv.androidroom.utils

import android.content.Context
import android.os.Build

class Utility {

    companion object {

        fun getToolbarHeight(context: Context): Int {

            val styledAttributes =
                context.theme.obtainStyledAttributes(intArrayOf(com.google.android.material.R.attr.actionBarSize))
            val toolbarHeight = styledAttributes.getDimension(0, 0f).toInt()
            styledAttributes.recycle()

            return toolbarHeight
        }

        fun isVanillaIceCreamAndUp(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM
        }
    }

}
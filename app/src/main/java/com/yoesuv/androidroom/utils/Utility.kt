package com.yoesuv.androidroom.utils

import android.content.Context
import android.os.Build
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

        fun insetsPadding(
            view: View, left: Boolean = false,
            top: Boolean = false,
            right: Boolean = false,
            bottom: Boolean = false,
            @ColorInt color: Int? = null,
        ) {
            ViewCompat.setOnApplyWindowInsetsListener(view) { v, windowInset ->
                val inset = windowInset.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(
                    if (left) inset.left else v.paddingLeft,
                    if (top) inset.top else v.paddingTop,
                    if (right) inset.right else v.paddingRight,
                    if (bottom) inset.bottom else v.paddingBottom
                )
                color?.let {
                    v.setBackgroundColor(it)
                }
                windowInset
            }
        }
    }

}
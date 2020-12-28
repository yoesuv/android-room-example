package com.yoesuv.androidroom.utils

import android.content.Context
import com.google.android.material.appbar.AppBarLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.util.AttributeSet
import android.view.View

class CustomFavBehavior(context: Context?, attrs: AttributeSet?) : androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior<FloatingActionButton>(context, attrs) {


    private val height = Utility.getToolbarHeight(context!!)

    override fun layoutDependsOn(parent: androidx.coordinatorlayout.widget.CoordinatorLayout, child: FloatingActionButton, dependency: View): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(parent: androidx.coordinatorlayout.widget.CoordinatorLayout, child: FloatingActionButton, dependency: View): Boolean {
        if (dependency is AppBarLayout) {
            val lp = child.layoutParams as androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams
            val bottomMargin = lp.bottomMargin
            val distanceToScroll = child.height + bottomMargin
            val ratio = dependency.y / height
            child.translationY = -distanceToScroll * ratio
        }
        return true
    }

}
package com.yoesuv.androidroom.menu.task.viewmodels

import android.app.Dialog
import android.util.Log
import android.view.View
import com.yoesuv.androidroom.data.AppConstant

class PopupInsertTaskViewModel(private val dialog: Dialog) {

    fun clickCancel(view: View){
        Log.d(AppConstant.TAG_DEBUG,"PopupInsertTaskViewModel # click cancel")
        dialog.dismiss()
    }

    fun clickSave(view: View){
        Log.d(AppConstant.TAG_DEBUG,"PopupInsertTaskViewModel # click save")
    }
}
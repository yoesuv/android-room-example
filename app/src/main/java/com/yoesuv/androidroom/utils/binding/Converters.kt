package com.yoesuv.androidroom.utils.binding

import android.databinding.BindingAdapter
import android.databinding.ObservableField
import android.support.v7.widget.AppCompatEditText
import com.yoesuv.androidroom.R

class Converters {

    companion object {

        @JvmStatic
        @BindingAdapter("binding")
        fun bindAppCompatEditText(appCompatEditText: AppCompatEditText, string: ObservableField<String>){
            val pair: Pair<ObservableField<String>, TextWatcherAdapter>? = appCompatEditText.getTag(R.id.bound_observable) as Pair<ObservableField<String>, TextWatcherAdapter>?
            if (pair==null || pair.first != string) {
                if (pair != null) {
                    appCompatEditText.removeTextChangedListener(pair.second)
                }
                val watcher = object : TextWatcherAdapter(){
                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        super.onTextChanged(p0, p1, p2, p3)
                        string.set(p0?.toString())
                    }
                }
                appCompatEditText.setTag(R.id.bound_observable, Pair(string, watcher))
                appCompatEditText.addTextChangedListener(watcher)
            }
            val newValue = string.get()
            if (appCompatEditText.text.toString() != newValue){
                appCompatEditText.setText(newValue)
            }
        }
    }

}
package com.yoesuv.androidroom.menu.task.rooms

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class MyTaskRoom (val task: String?, val content: String?){
    @PrimaryKey(autoGenerate = true)
    var idTask: Int? = null
}




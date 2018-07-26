package com.yoesuv.androidroom.menu.task.rooms

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class MyTask {

    @PrimaryKey(autoGenerate = true)
    var idTask: Int? = null
    var task: String? = null

}




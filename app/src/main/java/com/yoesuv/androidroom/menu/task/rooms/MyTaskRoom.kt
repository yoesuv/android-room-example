package com.yoesuv.androidroom.menu.task.rooms

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MyTaskRoom (var task: String?, var content: String?){
    @PrimaryKey(autoGenerate = true)
    var idTask: Int? = null
}




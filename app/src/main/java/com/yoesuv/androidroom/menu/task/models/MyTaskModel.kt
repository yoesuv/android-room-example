package com.yoesuv.androidroom.menu.task.models

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_tasks")
@Keep
data class MyTaskModel(
        @PrimaryKey(autoGenerate = true) var idTask: Int = 0,
        var titleTask: String?,
        var contentTask: String?
)
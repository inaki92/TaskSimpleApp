package com.inaki.todolistapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// annotation to add parcelable implementation
// you need to add parcelize plugin in gradle file to enable this feature
@Parcelize
data class PojoTodo(
    var task: String,
    var category: String
) : Parcelable
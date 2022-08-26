package com.ina.todolist.extension

import androidx.lifecycle.MutableLiveData
import java.lang.IllegalStateException

inline fun <T> MutableLiveData<T>.reduce(block: (T) -> T) {
    value = value?.let(block) ?: throw IllegalStateException("Value can't be null")
}
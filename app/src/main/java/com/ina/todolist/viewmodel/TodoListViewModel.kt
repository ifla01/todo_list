package com.ina.todolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ina.todolist.model.TodoItem
import com.ina.todolist.extension.reduce

class TodoListViewModel : ViewModel() {
    private val _state = MutableLiveData(TodoListState())
    val state: LiveData<TodoListState> get() = _state

    fun onActionClick(content: String) {
        if(state.value?.isShowingInput == true) addIfNotBlankOrDismiss(content)
        else showInput()
    }

    fun removeTodoItem(item: TodoItem) = _state.reduce { currentState ->
        TodoListState(dataSet = currentState.dataSet - item)
    }

    private fun addIfNotBlankOrDismiss(content: String) {
        if (content.isNotBlank()) addTodoItem(content)
        else _state.reduce { it.copy(isShowingInput = false) }
    }

    private fun showInput() = _state.reduce { currentState ->
        currentState.copy(isShowingInput = true)
    }

    private fun addTodoItem(content: String) = _state.reduce { currentState ->
        TodoListState(dataSet = currentState.dataSet + TodoItem(content = content))
    }
}

data class TodoListState(
    val isShowingInput: Boolean = false,
    val dataSet: List<TodoItem> = emptyList()
)
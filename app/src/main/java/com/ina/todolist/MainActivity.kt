package com.ina.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.lifecycle.ViewModelProvider
import com.ina.todolist.component.TodoListScreen
import com.ina.todolist.viewmodel.TodoListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this).get(TodoListViewModel::class.java)

        setContent {
            TodoListScreen(
                stateLiveData = viewModel.state,
                onActionClick = viewModel::onActionClick,
                onItemClick = viewModel::removeTodoItem
            )
        }
    }
}
package com.ina.todolist.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.itemsIndexed
import com.ina.todolist.model.TodoItem
import com.ina.todolist.ui.ComposeTheme

@Preview(
    name = "Todo List Default Preview",
    showBackground = true
)

@Composable
fun TodoListDefaultPreview() {
    ComposeTheme {
        TodoListDisplay(
            dataSet = listOf(
                TodoItem("Item 1"),
                TodoItem("Item 2")
            )
        ) {}
    }
}

@Composable
fun TodoListDisplay (
    modifier: Modifier = Modifier,
    dataSet: List<TodoItem>,
    onItemClick: (TodoItem) -> Unit
) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(dataSet) { index, item ->
            TodoItemDisplay(item = item, onItemClick = onItemClick)
        }
    }
}
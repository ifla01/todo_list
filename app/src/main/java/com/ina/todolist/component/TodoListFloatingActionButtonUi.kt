package com.ina.todolist.component

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.FloatingActionButton
import com.ina.todolist.ui.ComposeTheme

@Preview(
    name = "Add Button",
    showBackground = true
)
@Composable
fun AddTodoListFloatingActionButtonPreview() {
    TodoListFloatingActionButtonPreview(isActionAddItem = true)
}

@Preview(
    name = "Confirm Button",
    showBackground = true
)
@Composable
fun ConfirmTodoListFloatingActionButtonPreview() {
    TodoListFloatingActionButtonPreview(isActionAddItem = false)
}

@Composable
fun TodoListFloatingActionButtonPreview(isActionAddItem: Boolean) {
    ComposeTheme {
        TodoListFloatingActionButton(onClick = {}, isActionAddItem = isActionAddItem)
    }
}

@Composable
fun TodoListFloatingActionButton(onClick: () -> Unit, isActionAddItem: Boolean) {
    FloatingActionButton(onClick = onClick) {
        val currentIcon = if (isActionAddItem) Icons.Filled.Add else Icons.Filled.Check
        Icon(currentIcon, "Add Icon")
    }
}
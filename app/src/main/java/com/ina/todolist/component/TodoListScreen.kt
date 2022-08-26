package com.ina.todolist.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.compose.ui.platform.LocalContext
import com.ina.todolist.R
import com.ina.todolist.model.TodoItem
import com.ina.todolist.ui.ComposeTheme
import com.ina.todolist.viewmodel.TodoListState

@Preview(
    name = "Todo List Screen Default Preview",
    device = Devices.NEXUS_5,
    showBackground = true
)
@Composable
fun TodoListScreenDefaultPreview() {
    TodoListScreenPreview(showInput = false)
}

@Preview(
    name = "Todo List Screen Showing Input Preview",
    device = Devices.NEXUS_5,
    showBackground = true
)
@Composable
fun TodoListScreenShowingInputPreview() {
    TodoListScreenPreview(showInput = true)
}


@Composable
fun TodoListScreenPreview(showInput: Boolean) {
    val fakeDataSet = listOf(TodoItem(content = "Item 1"), TodoItem(content = "Item 2"))
    val fakeStateLiveData = MutableLiveData(
        TodoListState(isShowingInput = showInput, dataSet = fakeDataSet)
    )
    TodoListScreen(
        stateLiveData = fakeStateLiveData,
        onActionClick = {},
        onItemClick = {}
    )
}

@Composable
fun TodoListScreen (
    stateLiveData: LiveData<TodoListState>,
    onActionClick: (String) -> Unit,
    onItemClick: (TodoItem) -> Unit
){
    val resources = LocalContext.current.resources

    var currentInputTextState = remember { mutableStateOf("") }
    val state by stateLiveData.observeAsState(initial = TodoListState())

    if(!state.isShowingInput) {
        currentInputTextState.value = ""
    }

    ComposeTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = resources.getString(R.string.todo_list))})
            },
            floatingActionButton = {
                TodoListFloatingActionButton(onClick = { onActionClick(currentInputTextState.value)},
                    isActionAddItem = !state.isShowingInput
                )
            },
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                TodoListDisplay(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    dataSet = state.dataSet,
                    onItemClick= { item -> onItemClick(item) }
                )
                TodoItemToggleableInput(
                    shouldShow = state.isShowingInput,
                    state = currentInputTextState,
                    onActionClick = { onActionClick(currentInputTextState.value) }
                )
            }
        }
    }
}
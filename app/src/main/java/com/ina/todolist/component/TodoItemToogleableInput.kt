package com.ina.todolist.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.ina.todolist.R

@Composable
fun TodoItemToggleableInput(
    shouldShow: Boolean,
    state: MutableState<String>,
    onActionClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    if (shouldShow) {
        Surface(color = MaterialTheme.colors.background) {
            OutlinedTextField(
                label = { Text(text = stringResource(id = R.string.what_to_do_q))},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background)
                    .padding(start = 16.dp, top = 8.dp, end = 96.dp, 16.dp),
                value = state.value,
                onValueChange = { state.value = it },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    capitalization = KeyboardCapitalization.Words
                ),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                    onActionClick()
                }),
            )
        }
    }
}
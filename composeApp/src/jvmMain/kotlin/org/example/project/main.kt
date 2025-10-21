package org.example.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    val windowState = remember { MyWindowState("Main window") }

    MyWindow(windowState)
}

@Composable
private fun MyWindow(
    state: MyWindowState
) = Window(
    onCloseRequest = { state.confirmClose() },
    title = state.title
) {
    MenuBar {
        Menu("Actions") {
            Item("Work1", onClick = state::openInputDialog)
            Item("Work2", onClick = state::openNumberDialog)
        }
    }

    Column(modifier = Modifier.padding(8.dp)) {
        if (state.inputText.isNotEmpty()) {
            Text("Entered text: ${state.inputText}")
        }
        Text("Selected number: ${state.selectedNumber}")
    }

    if (state.showInputDialog) {
        InputDialog(
            initialText = "",
            onConfirm = { text ->
                state.inputText = text
                state.showInputDialog = false
            },
            onDismiss = { state.showInputDialog = false }
        )
    }

    if (state.showNumberDialog) {
        NumberDialog(
            initialValue = state.selectedNumber,
            onConfirm = { number ->
                state.selectedNumber = number
                state.showNumberDialog = false
            },
            onDismiss = { state.showNumberDialog = false }
        )
    }
}

private class MyWindowState(
    val title: String
) {
    var showInputDialog by mutableStateOf(false)
    var inputText by mutableStateOf("")

    var showNumberDialog by mutableStateOf(false)
    var selectedNumber by mutableStateOf(50)

    fun confirmClose(): Nothing = kotlin.system.exitProcess(0)

    fun openInputDialog() { showInputDialog = true }
    fun openNumberDialog() { showNumberDialog = true }
}

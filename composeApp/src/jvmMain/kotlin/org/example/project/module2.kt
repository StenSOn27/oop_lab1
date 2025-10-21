package org.example.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogWindow

@Composable
fun NumberDialog(
    initialValue: Int,
    onConfirm: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    var value by remember { mutableStateOf(initialValue.toFloat()) }

    DialogWindow(
        onCloseRequest = onDismiss,
        title = "Select a number"
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Selected: ${value.toInt()}", modifier = Modifier.padding(bottom = 8.dp))

            Slider(
                value = value,
                onValueChange = { value = it },
                valueRange = 1f..100f
            )

            Spacer(Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { onConfirm(value.toInt()) }) { Text("Yes") }
                Button(onClick = onDismiss) { Text("No") }
            }
        }
    }
}

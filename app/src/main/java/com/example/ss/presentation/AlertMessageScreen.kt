package com.example.ss.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ss.presentation.viewmodel.AlertMessageViewModel
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun AlertMessageScreen(navController: NavController, viewModel: AlertMessageViewModel = viewModel()) {
    var textState by remember { mutableStateOf(TextFieldValue(viewModel.currentMessage.value ?: "")) }
    val predefinedMessages by viewModel.predefinedMessages.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState()), // Enables scrolling
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Current Alert Message Display
        Text(text = "Currently Set to", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = viewModel.currentMessage.value ?: "I might be in danger...", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Edit Alert Message with ✓ Save Button
        BasicTextField(
            value = textState,
            onValueChange = { textState = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.LightGray)
                .padding(horizontal = 8.dp, vertical = 12.dp),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        innerTextField()
                    }
                    if (textState.text.isNotEmpty()) { // ✅ Show tick only if text is entered
                        IconButton(onClick = {
                            viewModel.updateMessage(textState.text)
                        }) {
                            Icon(imageVector = Icons.Default.Check, contentDescription = "Save Message")
                        }
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Predefined Messages
        Text(text = "Select Message", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        // Display predefined messages as buttons
        predefinedMessages.forEach { message ->
            Button(
                onClick = {
                    viewModel.updateMessage(message)
                    textState = TextFieldValue(message) // ✅ Set text field to selected message
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(message)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Save Button
        Button(onClick = { navController.popBackStack() }) {
            Text("Save")
        }
    }
}

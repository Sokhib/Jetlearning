package com.example.jetlearning


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Form screen composable
 */
@Composable
fun FormScreen(
    viewModel: UserViewModel,
    onNavigateToDetails: () -> Unit
) {
    // Collect the current state from the ViewModel
    val state by viewModel.state.collectAsState()

    // Main content
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.form_screen_title)) }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Name TextField
                OutlinedTextField(
                    value = state.userData.name,
                    onValueChange = { viewModel.processIntent(UserIntent.UpdateName(it)) },
                    label = { Text(stringResource(id = R.string.name_label)) },
                    placeholder = { Text(stringResource(id = R.string.name_placeholder)) },
                    modifier = Modifier.fillMaxWidth()
                )

                // Surname TextField
                OutlinedTextField(
                    value = state.userData.surname,
                    onValueChange = { viewModel.processIntent(UserIntent.UpdateSurname(it)) },
                    label = { Text(stringResource(id = R.string.surname_label)) },
                    placeholder = { Text(stringResource(id = R.string.surname_placeholder)) },
                    modifier = Modifier.fillMaxWidth()
                )

                // Error message if any
                if (state.error != null) {
                    Text(
                        text = state.error!!,
                        color = MaterialTheme.colors.error,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }

                // Submit Button
                Button(
                    onClick = {
                        viewModel.processIntent(UserIntent.SubmitForm)
                        if (state.userData.isValid()) {
                            onNavigateToDetails()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    enabled = !state.isLoading
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = MaterialTheme.colors.onPrimary
                        )
                    } else {
                        Text(text = stringResource(id = R.string.submit_button))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun FormScreenPreview() {
    FormScreen(
        viewModel = UserViewModel(),
        onNavigateToDetails = {}
    )
}
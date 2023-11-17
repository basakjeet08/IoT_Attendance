package iot.lab.qrdetails.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


/**
 * This function handles the Code Scanner Failure state UI.
 *
 * @param errorMessage This is the error message to be displayed to the user.
 * @param onSearchIconClick This function is invoked when the search Icon is pressed by the user
 * @param resetToIdleState This function is invoked when the user wants to re - open the Scanner
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodeScannerFailure(
    errorMessage: String,
    onSearchIconClick: (String) -> Unit,
    resetToIdleState: () -> Unit
) {

    // User Roll number input variable
    var input by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Error Message
        Text(text = errorMessage)

        // spacer of 16 dp
        Spacer(modifier = Modifier.height(16.dp))

        // user Roll number input Text Field
        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth(),
            value = input,
            onValueChange = { input = it },
            trailingIcon = {

                // Icon Button to search
                IconButton(
                    modifier = Modifier.clip(CircleShape),
                    onClick = { onSearchIconClick(input) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            },

            // Keyboard Options
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Search
            ),

            // Keyboard Actions
            keyboardActions = KeyboardActions(
                onSearch = { onSearchIconClick(input) }
            )
        )

        // Text Button Asking if the user wants to try the scanner again
        TextButton(onClick = resetToIdleState) {
            Text(text = "Try Again Using Scanner!!")
        }
    }
}
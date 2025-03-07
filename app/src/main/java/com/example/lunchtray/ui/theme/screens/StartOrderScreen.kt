package com.example.lunchtray.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lunchtray.R
import com.example.lunchtray.ui.theme.LunchTrayTheme

@Composable
fun StartOrderScreen(
    modifier: Modifier = Modifier,
    onStartOrderClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onStartOrderClick,
            modifier = Modifier.widthIn(250.dp)
        ) { Text(stringResource(R.string.start_order)) }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStartOrderScreen() {
    LunchTrayTheme {
        StartOrderScreen(onStartOrderClick = {})
    }
}
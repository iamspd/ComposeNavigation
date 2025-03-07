package com.example.lunchtray.ui.theme.screens

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lunchtray.R
import com.example.lunchtray.data.entrees
import com.example.lunchtray.data.model.LunchItem
import com.example.lunchtray.ui.theme.LunchTrayTheme

@Composable
fun SelectOptionsScreen(
    modifier: Modifier = Modifier,
    items: List<LunchItem>,
    subtotal: Float,
    onCancelClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
    onSelectionChange: (Int, Float) -> Unit = { _, _ -> }
) {
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items) {
                ListItemComponent(
                    lunchItem = it,
                    onItemClick = {
                        selectedItem = it.title
                        onSelectionChange(it.title, it.price)
                    },
                    isItemSelected = selectedItem == it.title
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            Text(
                modifier = Modifier.align(Alignment.End),
                text = stringResource(R.string.subtotal, subtotal),
                style = MaterialTheme.typography.bodyLarge
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = onCancelClick,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(stringResource(R.string.cancel_button))
                }

                Button(
                    onClick = onNextClick,
                    modifier = Modifier.weight(1f),
                    enabled = selectedItem != 0
                ) {
                    Text(stringResource(R.string.next_button))
                }
            }
        }


    }
}

@Composable
fun ListItemComponent(
    modifier: Modifier = Modifier,
    lunchItem: LunchItem,
    onItemClick: () -> Unit,
    isItemSelected: Boolean
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .selectable(
                    onClick = onItemClick,
                    selected = isItemSelected
                ),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                onClick = onItemClick,
                selected = isItemSelected
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = stringResource(lunchItem.title),
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    modifier = Modifier.padding(end = 28.dp),
                    text = stringResource(lunchItem.description),
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 3
                )

                Text(
                    text = stringResource(R.string.item_price, lunchItem.price),
                    style = MaterialTheme.typography.bodySmall
                )

                HorizontalDivider(thickness = 1.dp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSelectOptionsScreen() {
    LunchTrayTheme {
        SelectOptionsScreen(
            items = entrees,
            subtotal = 1.00f,
            onNextClick = {},
            onCancelClick = {},
            onSelectionChange = { _, _ -> }
        )
    }
}
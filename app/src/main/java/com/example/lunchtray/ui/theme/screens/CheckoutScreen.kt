package com.example.lunchtray.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lunchtray.R
import com.example.lunchtray.data.model.OrderState
import com.example.lunchtray.ui.theme.LunchTrayTheme

@Composable
fun CheckoutScreen(
    modifier: Modifier = Modifier,
    orderState: OrderState,
    tax: Float,
    total: Float,
    onCancelClick: () -> Unit,
    onSubmitClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.order_summary),
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            orderState.orderItems.forEach { item ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(item.first),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = stringResource(R.string.item_price, item.second),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            HorizontalDivider(thickness = 1.dp)

            Text(
                modifier = Modifier.align(Alignment.End),
                text = stringResource(R.string.subtotal, orderState.subtotal),
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                modifier = Modifier.align(Alignment.End),
                text = stringResource(R.string.tax, tax),
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                modifier = Modifier.align(Alignment.End),
                text = stringResource(R.string.total, total),
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = onCancelClick
            ) {
                Text(stringResource(R.string.cancel_button))
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = onSubmitClick
            ) {
                Text(stringResource(R.string.submit_button))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCheckoutScreen() {
    LunchTrayTheme {
        CheckoutScreen(
            orderState = OrderState(
                subtotal = 10.50f
            ),
            tax = 0.84f,
            total = 11.34f,
            onCancelClick = {},
            onSubmitClick = {}
        )
    }
}
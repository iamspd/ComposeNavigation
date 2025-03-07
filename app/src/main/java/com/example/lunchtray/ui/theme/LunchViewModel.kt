package com.example.lunchtray.ui.theme

import androidx.compose.runtime.currentComposer
import androidx.lifecycle.ViewModel
import com.example.lunchtray.data.model.LunchItem
import com.example.lunchtray.data.model.OrderState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val TAX_PERCENT = 0.13f

class LunchViewModel : ViewModel() {

    private val _orderState = MutableStateFlow(OrderState(item = LunchItem()))
    val orderState: StateFlow<OrderState> = _orderState.asStateFlow()

    private val orderList = mutableListOf<Pair<Int, Float>>()

    init {
        resetApp()
    }

    fun calculatePayable(): Float {
        return _orderState.value.subtotal.plus(calculateTax())
    }

    fun calculateTax(): Float {
        return _orderState.value.subtotal.times(TAX_PERCENT)
    }

    fun addSelectedItemsToOrderList(index: Int) {
        _orderState.update { currentState ->
            currentState.copy(
                orderItems = createOrderList(index),
                subtotal = calculateSubtotal()
            )
        }
    }

    private fun calculateSubtotal(): Float {
        return orderList.fold(0.00f) { total, item ->
            total.plus(item.second)
        }
    }

    private fun createOrderList(index: Int): List<Pair<Int, Float>> {
        if (orderList.size == 3) {
            orderList[index] = Pair(_orderState.value.item.title, _orderState.value.item.price)
        } else {
            orderList.add(Pair(_orderState.value.item.title, _orderState.value.item.price))
        }
        return orderList
    }

    fun selectOrderItem(itemTitle: Int, itemPrice: Float) {

        _orderState.update { currentState ->
            currentState.copy(
                item = LunchItem(
                    title = itemTitle,
                    price = itemPrice
                ),
                subtotal = itemPrice.plus(calculateSubtotal())
            )
        }
    }

    fun resetApp() {
        orderList.clear()
        _orderState.value = OrderState(
            orderItems = listOf(),
            subtotal = 0.00f
        )
    }
}
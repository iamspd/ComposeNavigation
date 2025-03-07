package com.example.lunchtray.data.model

import com.example.lunchtray.R

data class OrderState(
    val item: LunchItem = LunchItem(),
    val orderItems: List<Pair<Int, Float>> = mutableListOf(
        Pair(R.string.cauliflower, 7.00f),
        Pair(R.string.butternut_squash_soup, 3.00f),
        Pair(R.string.lunch_roll, 0.50f)
    ),
    val subtotal: Float = 0.00f
)

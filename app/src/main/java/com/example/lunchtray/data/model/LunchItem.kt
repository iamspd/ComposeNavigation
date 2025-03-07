package com.example.lunchtray.data.model

import androidx.annotation.StringRes

/**
 * Data class that holds the state of the app at a given screen.
 */
data class LunchItem(
    @StringRes val title: Int = 0,
    @StringRes val description: Int = 0,
    val price: Float = 0.00f
)

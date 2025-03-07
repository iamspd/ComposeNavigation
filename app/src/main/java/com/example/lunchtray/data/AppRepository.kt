package com.example.lunchtray.data

import com.example.lunchtray.R
import com.example.lunchtray.data.model.LunchItem

/**
 * List of entrees to display.
 */
val entrees = listOf(
    LunchItem(title = R.string.cauliflower, description = R.string.cauliflower_desc, price = 7.00f),
    LunchItem(
        title = R.string.three_bean_chilli,
        description = R.string.three_bean_chilli_desc,
        price = 4.00f
    ),
    LunchItem(
        title = R.string.mushroom_pasta, description = R.string.mushroom_pasta_desc, price = 5.50f
    ),
    LunchItem(
        title = R.string.spicy_black_bean_skillet,
        description = R.string.spicy_black_bean_skillet_desc,
        price = 5.50f
    ),
)

/**
 * List of side dishes to display.
 */
val sideDishes = listOf(
    LunchItem(title = R.string.summer_salad, description = R.string.summer_salad_desc, price = 2.50f),
    LunchItem(
        title = R.string.butternut_squash_soup,
        description = R.string.butternut_squash_soup_desc,
        price = 3.00f
    ),
    LunchItem(
        title = R.string.spicy_potatoes, description = R.string.spicy_potatoes_desc, price = 2.00f
    ),
    LunchItem(title = R.string.coconut_rice, description = R.string.coconut_rice_desc, price = 1.50f)
)

/**
 * List of accompaniment to display.
 */
val accompaniments = listOf(
    LunchItem(title = R.string.lunch_roll, description = R.string.lunch_roll_desc, price = 0.50f),
    LunchItem(
        title = R.string.mixed_berries, description = R.string.mixed_berries_desc, price = 1.00f
    ),
    LunchItem(
        title = R.string.pickled_veggies, description = R.string.pickled_veggies_desc, price = 0.50f
    )
)
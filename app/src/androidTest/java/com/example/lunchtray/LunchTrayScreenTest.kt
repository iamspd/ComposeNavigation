package com.example.lunchtray

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.lunchtray.data.entrees
import com.example.lunchtray.ui.theme.screens.SelectOptionsScreen
import com.example.lunchtray.utils.onNodeWithStringId
import org.junit.Rule
import org.junit.Test

class LunchTrayScreenTest {

    @get:Rule
    val composeScreenTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun appScreen_chooseEntreesScreen_verifyContent() {

        composeScreenTestRule.setContent {
            SelectOptionsScreen(items = entrees)
        }

        entrees.forEach {
            composeScreenTestRule.onNodeWithText(composeScreenTestRule.activity.getString(it.title)).assertIsDisplayed()
        }

        composeScreenTestRule.onNodeWithStringId(R.string.next_button).assertIsNotEnabled()
    }

}
package com.example.lunchtray

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.lunchtray.data.accompaniments
import com.example.lunchtray.data.entrees
import com.example.lunchtray.data.sideDishes
import com.example.lunchtray.utils.assertCurrentRouteName
import com.example.lunchtray.utils.onNodeWithStringId
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LunchTrayNavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            LunchTrayScreen(navController = navController)
        }
    }

    @Test
    fun appNavHost_verifyStartDestination_isStartOrderScreen() {
        navController.assertCurrentRouteName(AppScreen.Start.name)
    }

    @Test
    fun appNavHost_verifyStartOrderScreen_doesNotShowBackButton() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }

    @Test
    fun appNavHost_startOrderClick_navigateToChooseEntreesScreen() {
        composeTestRule.onNodeWithStringId(R.string.start_order)
            .performClick()
        navController.assertCurrentRouteName(AppScreen.Entree.name)
    }

    private fun navigateToChooseEntreesScreen() {
        composeTestRule.onNodeWithStringId(R.string.start_order)
            .performClick()
        composeTestRule.onNodeWithStringId(entrees[0].title)
            .performClick()
    }

    private fun navigateToChooseSideDishesScreen() {
        navigateToChooseEntreesScreen()
        composeTestRule.onNodeWithStringId(R.string.next_button)
            .performClick()
        composeTestRule.onNodeWithStringId(sideDishes[0].title)
            .performClick()
    }

    private fun navigateToChooseAccompanimentsScreen() {
        navigateToChooseSideDishesScreen()
        composeTestRule.onNodeWithStringId(R.string.next_button)
            .performClick()
        composeTestRule.onNodeWithStringId(accompaniments[0].title)
            .performClick()
    }

    @Test
    fun appNavHost_clickNextOnChooseEntreesScreen_navigateToChooseSideDishesScreen() {
        navigateToChooseSideDishesScreen()
        navController.assertCurrentRouteName(AppScreen.SideDish.name)
    }

    @Test
    fun appNavHost_clickNextOnChooseSideDishesScreen_navigateToChooseAccompanimentsScreen() {
        navigateToChooseAccompanimentsScreen()
        navController.assertCurrentRouteName(AppScreen.Accompaniment.name)
    }

    @Test
    fun appNavHost_clickNextOnChooseAccompanimentsScreen_navigateToCheckoutScreen() {
        navigateToChooseAccompanimentsScreen()
        composeTestRule.onNodeWithStringId(R.string.next_button)
            .performClick()
        navController.assertCurrentRouteName(AppScreen.Checkout.name)
    }

    private fun performNavigationUp() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText)
            .performClick()
    }

    @Test
    fun appNavHost_clickBackButtonOnChooseEntreesScreen_navigateToStartOrderScreen() {
        navigateToChooseEntreesScreen()
        performNavigationUp()
        navController.assertCurrentRouteName(AppScreen.Start.name)
    }

    private fun onCancelButtonClick() {
        composeTestRule.onNodeWithStringId(R.string.cancel_button)
            .performClick()
    }

    @Test
    fun appNavHost_clickBackButtonOnChooseSideDishesScreen_navigateToChooseEntreesScreen() {
        navigateToChooseSideDishesScreen()
        performNavigationUp()
        navController.assertCurrentRouteName(AppScreen.Entree.name)
    }

    @Test
    fun appNavHost_clickBackButtonOnChooseAccompanimentsScreen_navigateToChooseSideDishesScreen() {
        navigateToChooseAccompanimentsScreen()
        performNavigationUp()
        navController.assertCurrentRouteName(AppScreen.SideDish.name)
    }

    @Test
    fun appNavHost_clickBackButtonOnCheckoutScreen_navigateToChooseAccompanimentsScreen() {
        navigateToChooseAccompanimentsScreen()
        composeTestRule.onNodeWithStringId(R.string.next_button)
            .performClick()
        performNavigationUp()
        navController.assertCurrentRouteName(AppScreen.Accompaniment.name)
    }

    @Test
    fun appNavHost_clickCancelButtonOnChooseEntreesScreen_navigateToStartOrderScreen() {
        navigateToChooseEntreesScreen()
        onCancelButtonClick()
        navController.assertCurrentRouteName(AppScreen.Start.name)
    }

    @Test
    fun appNavHost_clickCancelButtonOnChooseSideDishesScreen_navigateToStartOrderScreen() {
        navigateToChooseSideDishesScreen()
        onCancelButtonClick()
        navController.assertCurrentRouteName(AppScreen.Start.name)
    }

    @Test
    fun appNavHost_clickCancelButtonOnChooseAccompanimentsScreen_navigateToStartOrderScreen() {
        navigateToChooseAccompanimentsScreen()
        onCancelButtonClick()
        navController.assertCurrentRouteName(AppScreen.Start.name)
    }

    @Test
    fun appNavHost_clickCancelButtonOnCheckoutScreen_navigateToStartOrderScreen() {
        navigateToChooseAccompanimentsScreen()
        composeTestRule.onNodeWithStringId(R.string.next_button)
            .performClick()
        onCancelButtonClick()
        navController.assertCurrentRouteName(AppScreen.Start.name)
    }
}
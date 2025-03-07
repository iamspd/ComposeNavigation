package com.example.lunchtray

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.data.accompaniments
import com.example.lunchtray.data.entrees
import com.example.lunchtray.data.sideDishes
import com.example.lunchtray.ui.theme.LunchViewModel
import com.example.lunchtray.ui.theme.screens.CheckoutScreen
import com.example.lunchtray.ui.theme.screens.SelectOptionsScreen
import com.example.lunchtray.ui.theme.screens.StartOrderScreen

enum class AppScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Entree(title = R.string.choose_entree),
    SideDish(title = R.string.choose_side_dish),
    Accompaniment(title = R.string.choose_accompaniment),
    Checkout(title = R.string.order_checkout)
}

@Composable
fun LunchTrayScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: LunchViewModel = viewModel()
) {

    val orderState by viewModel.orderState.collectAsState()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AppScreen.valueOf(
        backStackEntry?.destination?.route ?: AppScreen.Start.name
    )

    Scaffold(
        topBar = {
            LunchTrayAppTopBar(
                appScreen = currentScreen,
                canNavigateUp = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = AppScreen.Start.name
        ) {
            composable(route = AppScreen.Start.name) {
                StartOrderScreen(
                    onStartOrderClick = { navController.navigate(AppScreen.Entree.name) }
                )
            }

            composable(route = AppScreen.Entree.name) {
                SelectOptionsScreen(
                    items = entrees,
                    subtotal = orderState.subtotal,
                    onCancelClick = { cancelOrderAndNavigateToStart(viewModel, navController) },
                    onNextClick = {
                        viewModel.addSelectedItemsToOrderList(index = 0)
                        navController.navigate(AppScreen.SideDish.name)
                    },
                    onSelectionChange = { item, price ->
                        viewModel.selectOrderItem(itemTitle = item, itemPrice = price)
                    }
                )
            }

            composable(route = AppScreen.SideDish.name) {
                SelectOptionsScreen(
                    items = sideDishes,
                    subtotal = orderState.subtotal,
                    onCancelClick = { cancelOrderAndNavigateToStart(viewModel, navController) },
                    onNextClick = {
                        viewModel.addSelectedItemsToOrderList(index = 1)
                        navController.navigate(AppScreen.Accompaniment.name)
                    },
                    onSelectionChange = { item, price ->
                        viewModel.selectOrderItem(itemTitle = item, itemPrice = price)
                    }
                )
            }

            composable(route = AppScreen.Accompaniment.name) {
                SelectOptionsScreen(
                    items = accompaniments,
                    subtotal = orderState.subtotal,
                    onCancelClick = { cancelOrderAndNavigateToStart(viewModel, navController) },
                    onNextClick = {
                        viewModel.addSelectedItemsToOrderList(index = 2)
                        navController.navigate(AppScreen.Checkout.name)
                    },
                    onSelectionChange = { item, price ->
                        viewModel.selectOrderItem(itemTitle = item, itemPrice = price)
                    }
                )
            }

            composable(route = AppScreen.Checkout.name) {
                CheckoutScreen(
                    orderState = orderState,
                    tax = viewModel.calculateTax(),
                    total = viewModel.calculatePayable(),
                    onCancelClick = { cancelOrderAndNavigateToStart(viewModel, navController) },
                    onSubmitClick = {}
                )
            }
        }
    }
}

fun cancelOrderAndNavigateToStart(
    viewModel: LunchViewModel,
    navController: NavHostController
) {
    viewModel.resetApp()
    navController.popBackStack(route = AppScreen.Start.name, inclusive = false)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayAppTopBar(
    modifier: Modifier = Modifier,
    appScreen: AppScreen,
    canNavigateUp: Boolean,
    navigateUp: () -> Unit
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(appScreen.title),
                style = MaterialTheme.typography.bodyMedium
            )
        },
        navigationIcon = {
            if (canNavigateUp) {
                IconButton(
                    onClick = navigateUp
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}
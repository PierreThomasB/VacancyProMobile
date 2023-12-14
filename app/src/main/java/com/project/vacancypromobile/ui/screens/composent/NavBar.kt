package com.project.vacancypromobile.ui.screens.composent

import androidx.compose.foundation.background
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun NavBar(navController: NavController) {
    var selectedItem by remember { mutableIntStateOf(0) }

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.NewPeriod
    )
    NavigationBar(containerColor = MaterialTheme.colorScheme.primaryContainer) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.label)},
                label = { Text(item.label) },
                selected = selectedItem == index,
                onClick = { navController.navigate(item.route) },
            )
        }
    }
}
@Composable
@Preview
fun NavBarPreview() {
    NavBar(navController = rememberNavController())
}
package com.project.vacancypromobile.ui.screens.composent

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.project.vacancypromobile.ui.screens.Screen

sealed class BottomNavItem(val route: String, val icon:ImageVector, val label: String) {
    data object Home : BottomNavItem(Screen.Home.route, Icons.Filled.Home, "Home")
    data object NewPeriod : BottomNavItem(Screen.CreatePeriod.route, Icons.Filled.AddCircle, "New Period")

}

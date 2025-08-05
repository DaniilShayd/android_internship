package com.example.androidinternship.ui.screens.main

import com.example.androidinternship.navigation.NavRoutes
import com.example.androidinternship.navigation.TabItem

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    fun getSelectedTab(route: String?, tabs: List<TabItem>): Int {
        return tabs.indexOfFirst { tab ->
            route?.startsWith(tab.route) == true
        }.takeIf { it != -1 } ?: 0
    }

    fun shouldShowBottomBar(route: String?): Boolean {
        return NavRoutes.rootRoutes.any { rootRoute ->
            route?.startsWith(rootRoute) == true && route == rootRoute
        }
    }
}

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.androidinternship.navigation.*

@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {
    val tabs = TabItem.getTabs()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = remember(currentRoute) {
        NavRoutes.rootRoutes.any { rootRoute ->
            currentRoute?.startsWith(rootRoute) == true && currentRoute == rootRoute
        }
    }

    val selectedTab = getTabIndex(currentRoute)

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    tabs.forEachIndexed { index, tab ->
                        NavigationBarItem(
                            selected = selectedTab == index,
                            onClick = {
                                onTabIconClick(
                                    selectedTab = selectedTab,
                                    index = index,
                                    navController = navController,
                                    tab = tab,
                                )
                            },
                            icon = {
                                Icon(
                                    tab.icon, contentDescription = tab.title
                                )
                            },
                            label = {
                                Text(tab.title)
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        MainNavHost(navController = navController, modifier = Modifier.padding(paddingValues)
        )
    }
}

private fun getTabIndex(route: String?): Int {
    return when {
        route?.contains(TabItem.Posts.route) == true -> 0
        route?.contains(TabItem.Photos.route) == true -> 1
        route?.contains(TabItem.Todos.route) == true -> 2
        route?.contains(TabItem.Users.route) == true -> 3
        else -> 0
    }
}

private fun onTabIconClick(
    selectedTab: Int,
    index: Int,
    navController: NavHostController,
    tab: TabItem
) {
    if (selectedTab == index) {
        return
    }
    navController.navigate(tab.route) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
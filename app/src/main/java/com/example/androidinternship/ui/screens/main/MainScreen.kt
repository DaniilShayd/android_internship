import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
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

    val selectedTab = getTabIndex(currentRoute, tabs)

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavBar(
                    selectedTab = selectedTab,
                    tabs = tabs,
                    navController = navController
                )
            }
        }
    ) { paddingValues ->
        MainNavHost(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun BottomNavBar(
    selectedTab: Int,
    tabs: List<TabItem>,
    navController: NavHostController
) {
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
                        imageVector = tab.icon,
                        contentDescription = tab.title
                    )
                },
                label = {
                    Text(
                        text = tab.title,
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    }
}

private fun getTabIndex(route: String?, tabs: List<TabItem>): Int {
    return tabs.indexOfFirst { tab ->
        route?.startsWith(tab.route) == true
    }.takeIf { it != -1 } ?: 0
}

private fun onTabIconClick(
    selectedTab: Int,
    index: Int,
    navController: NavHostController,
    tab: TabItem
) {
    if (selectedTab == index) return

    navController.navigate(tab.route) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
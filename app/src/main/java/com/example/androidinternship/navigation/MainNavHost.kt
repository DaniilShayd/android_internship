package com.example.androidinternship.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import androidx.navigation.*
import com.example.androidinternship.ui.screens.album.AlbumScreen
import com.example.androidinternship.ui.screens.albums.AlbumsScreen
import com.example.androidinternship.ui.screens.createtodo.CreateTodoScreen
import com.example.androidinternship.ui.screens.photo.PhotoScreen
import com.example.androidinternship.ui.screens.post.PostScreen
import com.example.androidinternship.ui.screens.posts.PostsScreen
import com.example.androidinternship.ui.screens.splash.SplashScreen
import com.example.androidinternship.ui.screens.todos.TodosScreen
import com.example.androidinternship.ui.screens.user.UserScreen
import com.example.androidinternship.ui.screens.users.UsersScreen
import androidx.compose.animation.SharedTransitionLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidinternship.ui.screens.createtodo.CreateTodoViewModel
import com.example.androidinternship.ui.screens.post.PostViewModel
import com.example.androidinternship.ui.screens.todos.TodosViewModel
import kotlin.Int

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainNavHost(navController: NavHostController, modifier: Modifier = Modifier) {

    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = NavRoutes.SPLASH,
            modifier = modifier
        ) {
            composable(NavRoutes.SPLASH) {
                SplashScreen(onAnimationFinished = {
                    navController.navigate(NavRoutes.POSTS_MAIN) {
                        popUpTo(NavRoutes.SPLASH) {
                            inclusive = true
                        }
                    }
                })
            }
            navigation(
                startDestination = NavRoutes.POSTS_MAIN,
                route = NavRoutes.POSTS
            ) {
                composable(NavRoutes.POSTS_MAIN) {
                    PostsScreen(
                        navController = navController,
                    )
                }
                composable(
                    route = NavRoutes.POST_DETAIL,
                    arguments = listOf(navArgument(NavElements.POST_ID) {
                        type = NavType.IntType
                    })
                ) { backStackEntry ->
                    val viewModel: PostViewModel = viewModel(
                        factory = PostViewModelFactory(
                            backStackEntry.arguments?.getInt(NavElements.POST_ID) ?: 0
                        )
                    )

                    PostScreen(
                        navController = navController,
                        viewModel = viewModel,
                    )
                }
            }

            navigation(
                startDestination = NavRoutes.ALBUMS_MAIN,
                route = NavRoutes.ALBUMS
            ) {
                composable(NavRoutes.ALBUMS_MAIN) {
                    AlbumsScreen(
                        navController = navController,
                    )
                }
                composable(NavRoutes.ALBUM) {
                    AlbumScreen(
                        navController = navController,
                        albumId = it.arguments?.getString(NavElements.ALBUM_ID)?.toInt() ?: 0
                    )
                }
                composable(NavRoutes.PHOTO) {
                    PhotoScreen(
                        navController = navController,
                        photoId = it.arguments?.getString(NavElements.PHOTO_ID)?.toInt() ?: 0
                    )
                }
            }


            navigation(
                startDestination = NavRoutes.TODOS_MAIN,
                route = NavRoutes.TODOS
            ) {
                composable(NavRoutes.TODOS_MAIN) {
                    val viewModel: TodosViewModel = viewModel(
                        factory = TodosViewModelFactory()
                    )

                    TodosScreen(
                        navController = navController,
                        viewModel = viewModel
                    )
                }

                composable(
                    route = NavRoutes.TODO_DETAIL_WITH_INDEX,
                    arguments = listOf(
                        navArgument("index") {
                            type = NavType.StringType
                            nullable = true
                            defaultValue = null
                        }
                    )
                ) { backStackEntry ->
                    val index = backStackEntry.arguments?.getString("index")?.toIntOrNull()
                    val viewModel: CreateTodoViewModel = viewModel(
                        factory = CreateTodoViewModelFactory(index)
                    )

                    CreateTodoScreen(navController = navController, viewModel = viewModel)
                }

            }


            navigation(
                startDestination = NavRoutes.USERS_MAIN,
                route = NavRoutes.USERS
            ) {
                composable(NavRoutes.USERS_MAIN) {
                    UsersScreen(navController = navController,
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedContentScope = this@composable,
                        )
                }
                composable(
                    route = NavRoutes.USER_DETAIL,
                    arguments = listOf(
                        navArgument(NavElements.USER_ID) {
                            type = NavType.IntType
                        }
                    )
                ) { backStackEntry ->
                    val userId = backStackEntry.arguments?.getInt(NavElements.USER_ID) ?: 0
                    UserScreen(
                        userId = userId,
                        navController = navController,
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedContentScope = this@composable
                    )
                }
            }
        }
    }

}


class TodosViewModelFactory(
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TodosViewModel() as T
    }
}

class PostViewModelFactory(
    private val postId: Int
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostViewModel(postId = postId) as T
    }
}

class CreateTodoViewModelFactory(
    private val index: Int?
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateTodoViewModel(index) as T
    }
}


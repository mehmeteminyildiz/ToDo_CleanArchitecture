package com.my.todoclean

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.my.todoclean.feature_todo.presentation.add_edit_todo.AddEditTodoScreen
import com.my.todoclean.feature_todo.presentation.todos.TodosScreen
import com.my.todoclean.feature_todo.presentation.util.Screen
import com.my.todoclean.ui.theme.ToDoCleanTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoCleanTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController, startDestination = Screen.TodosScreen.route
                    ) {
                        composable(route = Screen.TodosScreen.route) {
                            TodosScreen(navController = navController)
                        }

                        composable(
                            route = Screen.AddEditTodoScreen.route + "?todoId={todoId}&todoColor={todoColor}",
                            arguments = listOf(
                                navArgument(
                                    name = "todoId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "todoColor"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ) {
                            val color = it.arguments?.getInt("todoColor") ?: -1
                            AddEditTodoScreen(navController = navController, todoColor = color)
                        }

                    }

                }
            }
        }
    }
}

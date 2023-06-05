package com.my.todoclean.feature_todo.domain.use_case

data class TodoUseCases(
    val getTodos: GetTodos,
    val deleteTodo: DeleteTodo,
    val addTodo: AddTodo,
    val getTodo: GetTodo,
    val updateStatus: UpdateStatus
)

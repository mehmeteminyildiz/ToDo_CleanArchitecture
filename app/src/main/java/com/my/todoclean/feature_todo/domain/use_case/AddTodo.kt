package com.my.todoclean.feature_todo.domain.use_case

import com.my.todoclean.feature_todo.domain.model.InvalidTodoException
import com.my.todoclean.feature_todo.domain.model.Todo
import com.my.todoclean.feature_todo.domain.repository.TodoRepository

class AddTodo(
    private val repository: TodoRepository
) {

    suspend operator fun invoke(todo: Todo) {
        if (todo.title.isBlank()) {
            throw InvalidTodoException("The title of the to do can not be empty")
        }
        if (todo.content.isBlank()) {
            throw InvalidTodoException("The content of the to do can not be empty")
        }
        repository.insertTodo(todo = todo)
    }
}
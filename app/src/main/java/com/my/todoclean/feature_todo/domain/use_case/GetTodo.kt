package com.my.todoclean.feature_todo.domain.use_case

import com.my.todoclean.feature_todo.domain.model.Todo
import com.my.todoclean.feature_todo.domain.repository.TodoRepository

class GetTodo(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(id: Int): Todo? {
        return repository.getTodoById(id = id)
    }
}
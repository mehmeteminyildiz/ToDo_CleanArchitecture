package com.my.todoclean.feature_todo.domain.use_case

import com.my.todoclean.feature_todo.domain.model.Todo
import com.my.todoclean.feature_todo.domain.repository.TodoRepository

class UpdateStatus(
    private val repository: TodoRepository

) {
    suspend operator fun invoke(todo: Todo) {
        repository.updateIsCompletedStatus(todo)
    }
}
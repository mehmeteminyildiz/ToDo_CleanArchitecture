package com.my.todoclean.feature_todo.data.repository

import com.my.todoclean.feature_todo.data.data_source.TodoDao
import com.my.todoclean.feature_todo.domain.model.Todo
import com.my.todoclean.feature_todo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(
    private val dao: TodoDao
) : TodoRepository {

    override fun getTodos(): Flow<List<Todo>> {
        return dao.getTodos()
    }

    override suspend fun getTodoById(id: Int): Todo? {
        return dao.getTodoById(id = id)
    }

    override suspend fun insertTodo(todo: Todo) {
        dao.insertTodo(todo = todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        dao.deleteTodo(todo = todo)
    }

    override suspend fun updateIsCompletedStatus(todo: Todo) {
        dao.updateIsCompletedStatus(todo)
    }
}
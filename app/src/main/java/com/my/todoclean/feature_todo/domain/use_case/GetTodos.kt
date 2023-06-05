package com.my.todoclean.feature_todo.domain.use_case

import com.my.todoclean.feature_todo.domain.model.Todo
import com.my.todoclean.feature_todo.domain.repository.TodoRepository
import com.my.todoclean.feature_todo.domain.util.OrderType
import com.my.todoclean.feature_todo.domain.util.TodoOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTodos(
    private val repository: TodoRepository
) {
    operator fun invoke(
        todoOrder: TodoOrder = TodoOrder.Date(OrderType.Descending)
    ) : Flow<List<Todo>>{
        return repository.getTodos().map { todos ->
            when(todoOrder.orderType){
                is OrderType.Ascending -> {
                    when(todoOrder){
                        is TodoOrder.Title -> todos.sortedBy { it.title.lowercase() }
                        is TodoOrder.Date -> todos.sortedBy { it.timestamp }
                        is TodoOrder.Color -> todos.sortedBy { it.color }
                    }
                }
                is OrderType.Descending -> {
                    when(todoOrder){
                        is TodoOrder.Title -> todos.sortedByDescending { it.title.lowercase() }
                        is TodoOrder.Date -> todos.sortedByDescending { it.timestamp }
                        is TodoOrder.Color -> todos.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}
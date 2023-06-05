package com.my.todoclean.feature_todo.presentation.todos

import com.my.todoclean.feature_todo.domain.model.Todo
import com.my.todoclean.feature_todo.domain.util.OrderType
import com.my.todoclean.feature_todo.domain.util.TodoOrder

data class TodosState(
    val todos: List<Todo> = emptyList(),
    val todoOrder: TodoOrder = TodoOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)

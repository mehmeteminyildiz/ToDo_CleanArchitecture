package com.my.todoclean.feature_todo.presentation.todos.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.my.todoclean.feature_todo.domain.util.OrderType
import com.my.todoclean.feature_todo.domain.util.TodoOrder


@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    todoOrder: TodoOrder = TodoOrder.Date(OrderType.Descending),
    onOrderChange: (TodoOrder) -> Unit
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = "Title",
                selected = todoOrder is TodoOrder.Title,
                onSelect = {
                    onOrderChange(TodoOrder.Title(todoOrder.orderType))
                },
                modifier = Modifier
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Date",
                selected = todoOrder is TodoOrder.Date,
                onSelect = {
                    onOrderChange(TodoOrder.Date(todoOrder.orderType))
                },
                modifier = Modifier
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Color",
                selected = todoOrder is TodoOrder.Color,
                onSelect = {
                    onOrderChange(TodoOrder.Color(todoOrder.orderType))
                },
                modifier = Modifier
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = "Ascending",
                selected = todoOrder.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(todoOrder.copy(OrderType.Ascending))
                },
                modifier = Modifier
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = todoOrder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(todoOrder.copy(OrderType.Descending))
                },
                modifier = Modifier
            )
        }

    }

}
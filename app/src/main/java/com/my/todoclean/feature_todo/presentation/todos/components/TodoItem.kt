package com.my.todoclean.feature_todo.presentation.todos.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.my.todoclean.feature_todo.domain.model.Todo
import com.my.todoclean.ui.theme.SelectedRB
import com.my.todoclean.ui.theme.UnselectedRB

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TodoItem(
    todo: Todo,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit,
    onCheckClick: (newStatus: Boolean) -> Unit
) {
    val isCompletedState = remember { mutableStateOf(todo.isCompleted) }


    Box(
        modifier = modifier.background(color = Color(todo.color))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = isCompletedState.value, onClick = {
                    isCompletedState.value = !todo.isCompleted
                    onCheckClick(!todo.isCompleted)
                }, colors = RadioButtonDefaults.colors(
                    selectedColor = SelectedRB, unselectedColor = UnselectedRB
                )
            )
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                        .padding(end = 32.dp)

                ) {
                    // Title
                    Text(
                        text = todo.title,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Content
                    Text(
                        text = todo.content,
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onSurface,
                        maxLines = 10,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                MyDropdown(onItemClick = onDeleteClick)
            }
        }
    }
}


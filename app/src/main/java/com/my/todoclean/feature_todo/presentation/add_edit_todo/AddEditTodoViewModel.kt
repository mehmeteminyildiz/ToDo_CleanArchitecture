package com.my.todoclean.feature_todo.presentation.add_edit_todo

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.todoclean.feature_todo.domain.model.InvalidTodoException
import com.my.todoclean.feature_todo.domain.model.Todo
import com.my.todoclean.feature_todo.domain.use_case.TodoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel
@Inject constructor(
    private val todoUseCases: TodoUseCases, savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _todoTitle = mutableStateOf(TodoTextFieldState(hint = "Enter title..."))
    val todoTitle: State<TodoTextFieldState> = _todoTitle

    private val _todoContent = mutableStateOf(TodoTextFieldState(hint = "Enter content..."))
    val todoContent: State<TodoTextFieldState> = _todoContent

    private val _todoColor = mutableStateOf(Todo.todoColors.random().toArgb())
    val todoColor: State<Int> = _todoColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentTodoId: Int? = null

    init {
        savedStateHandle.get<Int>("todoId")?.let { todoId ->
            if (todoId != -1) {
                viewModelScope.launch {
                    todoUseCases.getTodo(todoId)?.also { todo ->
                        currentTodoId = todo.id
                        _todoTitle.value = todoTitle.value.copy(
                            text = todo.title, isHintVisible = false
                        )
                        _todoContent.value = todoContent.value.copy(
                            text = todo.content, isHintVisible = false
                        )
                        _todoColor.value = todo.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditTodoEvent) {
        when (event) {
            is AddEditTodoEvent.EnteredTitle -> {
                _todoTitle.value = todoTitle.value.copy(text = event.value)
            }

            is AddEditTodoEvent.ChangeTitleFocus -> {
                _todoTitle.value =
                    todoTitle.value.copy(isHintVisible = !event.focusState.isFocused && todoTitle.value.text.isBlank())
            }

            is AddEditTodoEvent.EnteredContent -> {
                _todoContent.value = todoContent.value.copy(text = event.value)
            }

            is AddEditTodoEvent.ChangeContentFocus -> {
                _todoContent.value =
                    todoContent.value.copy(isHintVisible = !event.focusState.isFocused && todoContent.value.text.isBlank())
            }

            is AddEditTodoEvent.ChangeColor -> {
                _todoColor.value = event.color
            }

            is AddEditTodoEvent.SaveTodo -> {
                viewModelScope.launch {
                    try {
                        todoUseCases.addTodo(
                            Todo(
                                title = todoTitle.value.text,
                                content = todoContent.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = todoColor.value,
                                id = currentTodoId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveTodo)
                    } catch (e: InvalidTodoException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Couldn't save to doTodosViewModel"
                            )
                        )
                    }
                }
            }

        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object SaveTodo : UiEvent()
    }
}
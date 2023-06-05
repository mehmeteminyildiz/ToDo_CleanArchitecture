package com.my.todoclean.feature_todo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.my.todoclean.ui.theme.BabyBlue
import com.my.todoclean.ui.theme.LightGreen
import com.my.todoclean.ui.theme.RedOrange
import com.my.todoclean.ui.theme.RedPink
import com.my.todoclean.ui.theme.Violet

@Entity
data class Todo(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val todoColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidTodoException(message: String) : Exception(message)
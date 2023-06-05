package com.my.todoclean.di

import android.app.Application
import androidx.room.Room
import com.my.todoclean.feature_todo.data.data_source.TodoDatabase
import com.my.todoclean.feature_todo.data.repository.TodoRepositoryImpl
import com.my.todoclean.feature_todo.domain.repository.TodoRepository
import com.my.todoclean.feature_todo.domain.use_case.AddTodo
import com.my.todoclean.feature_todo.domain.use_case.DeleteTodo
import com.my.todoclean.feature_todo.domain.use_case.GetTodo
import com.my.todoclean.feature_todo.domain.use_case.GetTodos
import com.my.todoclean.feature_todo.domain.use_case.TodoUseCases
import com.my.todoclean.feature_todo.domain.use_case.UpdateStatus
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(
        app: Application
    ): TodoDatabase {
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            TodoDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: TodoDatabase): TodoRepository {
        return TodoRepositoryImpl(db.todoDao)
    }

    @Provides
    @Singleton
    fun provideTodoUseCases(repository: TodoRepository): TodoUseCases {
        return TodoUseCases(
            getTodos = GetTodos(repository),
            deleteTodo = DeleteTodo(repository),
            addTodo = AddTodo(repository),
            getTodo = GetTodo(repository),
            updateStatus = UpdateStatus(repository)
        )
    }
}
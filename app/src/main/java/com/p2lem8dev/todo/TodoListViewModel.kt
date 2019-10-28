package com.p2lem8dev.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoListViewModel : ViewModel() {
    private var mTodoItems: LiveData<List<TodoItem>> = MutableLiveData()

    val todos: LiveData<List<TodoItem>>
        get() {
            if (mTodoItems.value == null || mTodoItems.value!!.isEmpty()) {
                load()
            }
            return mTodoItems
        }

    private fun load() {
        TodoRepository.repository.loadTodoItems { items ->
            mTodoItems = MutableLiveData(items)
        }
    }

    fun addTodoItem(text: String) {
        TodoRepository.repository.createTodoItem(TodoItem.create(text)) { load() }
    }

    fun update(todoItem: TodoItem) {
        TodoRepository.repository.updateTodoItem(todoItem)
    }

    fun delete(todoItem: TodoItem) {
        TodoRepository.repository.removeTodoItem(todoItem)
    }
}

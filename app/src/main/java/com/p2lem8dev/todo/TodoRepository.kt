package com.p2lem8dev.todo

import com.google.gson.Gson
import java.util.*
import kotlin.collections.ArrayList

class TodoRepository : FileSystemRepository(".") {
    private var items = ArrayList<TodoItem>()
    private var todoItemsFilename = "todos.json"

    private val commonFilename: String
        get() ="$commonDirectory/$todoItemsFilename"

    fun loadTodoItems(onSuccess: (todoItem: List<TodoItem>) -> Unit) {
        if (items.size == 0) { load() }
        onSuccess(items)
    }

    fun createTodoItem(todoItem: TodoItem, then: (TodoRepository) -> Unit) {
        items.add(todoItem)
        saveChanges()
        then(this)
    }

    fun removeTodoItem(todoItem: TodoItem) {
        if (items.size == 0) return
        val index = items.indexOfFirst { it.id == todoItem.id }
        if (index == -1 || index >= items.size) return
        items.removeAt(index)
        saveChanges()
    }

    fun updateTodoItem(todoItem: TodoItem) {
        val item = items.find { it.id == todoItem.id } ?: return
        item.completed = todoItem.completed
        saveChanges()
    }

    fun configure(
        applicationDirectory: String
    ) {
        this.commonDirectory = applicationDirectory
    }

    private fun load() {
        items.clear()
        val jsonStr = readFromFile(commonFilename)
        if (jsonStr != null && jsonStr.isNotEmpty()) {
            items.addAll(Gson().fromJson(jsonStr, Array<TodoItem>::class.java))
        }
    }

    private fun saveChanges() {
        writeToFile(commonFilename, Gson().toJson(items))
    }

    companion object {
        val repository = TodoRepository()
    }
}
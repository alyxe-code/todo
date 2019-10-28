package com.p2lem8dev.todo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.InputEvent
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoListViewModel: TodoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TodoRepository.repository.configure(filesDir.absolutePath)

        todoListViewModel = ViewModelProviders.of(this).get(TodoListViewModel::class.java)

        todo_input.setOnKeyListener { view, key, event ->
            if (event.action == KeyEvent.ACTION_UP && key == KeyEvent.KEYCODE_ENTER) {
                val im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                im.hideSoftInputFromWindow(view.windowToken, 0)
                todo_input.clearFocus()

                todo_input.text!!.let {
                    todoListViewModel.addTodoItem(it.toString())
                    it.clear()
                }
            }

            return@setOnKeyListener true
        }
    }
}

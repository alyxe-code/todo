package com.p2lem8dev.todo

import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RelativeLayout
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.p2lem8dev.todo.databinding.TodoListItemBinding

class TodoListAdapter(private val todoListFragment: TodoListFragment) :
    RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {

    private val todoListViewModel: TodoListViewModel =
        ViewModelProviders.of(todoListFragment).get(TodoListViewModel::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = TodoListItemBinding.inflate(LayoutInflater.from(parent.context))
        return TodoViewHolder(binding.root, fun() { this.notifyDataSetChanged() }, binding,
            todoListViewModel)
    }

    override fun getItemCount(): Int {
        return todoListViewModel.todos.value?.size ?: 0
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todoListViewModel.todos.value!![position])
    }

    class TodoViewHolder(
        view: View,
        private val notifyDataSetChanged: () -> Unit,
        private val binding: TodoListItemBinding,
        private val todoListViewModel: TodoListViewModel
    ) : RecyclerView.ViewHolder(view) {

        private lateinit var mTodoItem: TodoItem

        fun bind(todoItem: TodoItem) {
            mTodoItem = todoItem
            binding.todoItem = mTodoItem
            binding.completed = mTodoItem.completed
            binding.isSelected = false
            binding.presenter = this;

            itemView.findViewById<CheckBox>(R.id.todo_complete).setOnCheckedChangeListener { _, isChecked ->
                mTodoItem.completed = isChecked
                binding.completed = isChecked
                todoListViewModel.update(mTodoItem)
            }

            enableGestures()
        }

        fun handleClick(view: View) {
            todoListViewModel.delete(mTodoItem)
            this.notifyDataSetChanged()
        }

        fun enableGestures() {
            itemView.rootView.setOnLongClickListener {
                binding.isSelected = true
                return@setOnLongClickListener false
            }
        }
    }
}

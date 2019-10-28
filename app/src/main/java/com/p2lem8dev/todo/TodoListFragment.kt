package com.p2lem8dev.todo

import android.graphics.Rect
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.p2lem8dev.todo.helpers.decorators.RecyclerViewItemOffsetDecorator


class TodoListFragment : Fragment() {

    companion object {
        fun newInstance() = TodoListFragment()
    }

    private lateinit var viewModel: TodoListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.todo_list_fragment, container, false)
        val todoList = view.findViewById<RecyclerView>(R.id.todo_list)

        todoList.layoutManager = LinearLayoutManager(context)
        todoList.adapter = TodoListAdapter(this)
        todoList.addItemDecoration(RecyclerViewItemOffsetDecorator(16))

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TodoListViewModel::class.java)
    }

}

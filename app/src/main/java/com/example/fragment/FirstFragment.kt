package com.example.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FirstFragment : Fragment(R.layout.fragment_first) {

    private lateinit var taskList: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        taskList = view.findViewById(R.id.fragtv1)
        return view
    }

    fun addTask(task: String) {
        Log.d("FirstFragment", "Adding task: $task")
        taskList.append("\n$task")
    }
}

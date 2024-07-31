package com.example.fragment

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), CustomDialogFragment.DialogListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()
        val thirdFragment = ThirdFragment()
        val fourthFragment = FourthFragment()

        val fragment1: Button = findViewById(R.id.fbtn1)
        val fragment2: Button = findViewById(R.id.fbtn2)
        val btn3: Button = findViewById(R.id.fbtn3)
        val btn4: Button = findViewById(R.id.fbtn4)

        fragment1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.FLfragment, firstFragment, "FIRST_FRAGMENT")
                addToBackStack(null)
                commit()
            }
        }

        fragment2.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.FLfragment, secondFragment, "SECOND_FRAGMENT")
                addToBackStack(null)
                commit()
            }
        }

        btn3.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.FLfragment, thirdFragment)
                addToBackStack(null)
                commit()
            }
        }

        btn4.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.FLfragment, fourthFragment)
                addToBackStack(null)
                commit()
            }
        }

        val dialog = CustomDialogFragment()
        val btn: Button = findViewById(R.id.btn)

        btn.setOnClickListener {
            dialog.show(supportFragmentManager, "dialog")
        }
    }

    private fun updateFragment(containerId: Int, task: String) {
        val fragment = supportFragmentManager.findFragmentByTag("FIRST_FRAGMENT") as? FirstFragment
        fragment?.addTask(task)
    }

    fun recievetext(todo: String){
        val fragtv1: TextView = findViewById(R.id.fragtv1)
        fragtv1.text = todo
    }

    override fun onDialogPositiveClick(task: String, priority: String) {
        // Update the FirstFragment with the new task
        when (priority) {
            "Urgent & Important",
            "Not Urgent, but Important",
            "Urgent, but Not Important",
            "Not Urgent, Not Important" -> {
                updateFragment(R.id.FLfragment, task)
            }
        }
    }
}

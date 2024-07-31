package com.example.fragment

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.DialogFragment

class CustomDialogFragment : DialogFragment(R.layout.fragment_dialogue) {

    var flag: String = "Select"
    interface DialogListener {
        fun onDialogPositiveClick(task: String, priority: String)
    }

    private lateinit var listener: DialogListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            listener = activity as DialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException((activity.toString() + " must implement DialogListener"))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinner: Spinner = view.findViewById(R.id.sppriority)
        val ettask: EditText = view.findViewById(R.id.edtask)
        val button: Button = view.findViewById(R.id.btnsave)

        button.setOnClickListener {
            val task = ettask.text.toString()
            listener.onDialogPositiveClick(task, flag)
            dismiss()
        }

        val options = arrayOf("Urgent & Important","Not Urgent, but Important","Urgent, but Not Important", "Not Urgent, Not Important")
        spinner.adapter =ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, options)

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                p0: AdapterView<*>?,
                p1: View?,
                p2: Int,
                p3: Long
            ) {
                flag = options.get(p2)
            }



            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }


        }

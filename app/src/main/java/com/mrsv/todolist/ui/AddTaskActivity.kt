package com.mrsv.todolist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.mrsv.todolist.databinding.ActivityAddTaskBinding
import com.mrsv.todolist.extensions.format
import com.mrsv.todolist.extensions.text
import java.util.*

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        insertListener()
    }

    private fun insertListener() {
        binding.addTaskDate.editText?.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                binding.addTaskDate.text = Date(it + offset).format()
            }
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }

        // TODO: Read how to config the 'addTaskTime' output layout as in 00:00 AM (or PM)
        
        binding.addTaskTime.editText?.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder().build()
            timePicker.addOnPositiveButtonClickListener {
                binding.addTaskTime.text = "${timePicker.hour}:${timePicker.minute}"
            }

            timePicker.show(supportFragmentManager, null)
        }

        binding.cancel.setOnClickListener {
            finish()
        }

        binding.saveTask.setOnClickListener {

        }
    }
}
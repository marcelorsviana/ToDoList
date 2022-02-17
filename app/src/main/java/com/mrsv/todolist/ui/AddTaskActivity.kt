package com.mrsv.todolist.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.mrsv.todolist.databinding.ActivityAddTaskBinding
import com.mrsv.todolist.datasource.TaskDataSource
import com.mrsv.todolist.extensions.format
import com.mrsv.todolist.extensions.text
import com.mrsv.todolist.model.Task
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
                val hour = if (timePicker.hour in 0..9) "0${timePicker.hour}" else timePicker.hour
                val minute = if(timePicker.minute in 0..9) "0${timePicker.minute}" else timePicker.minute
                binding.addTaskTime.text = "$hour:$minute"
            }

            timePicker.show(supportFragmentManager, null)
        }

        binding.cancel.setOnClickListener {
            finish()
        }

        binding.saveTask.setOnClickListener {
            val task = Task(
                title = binding.addTaskTitle.text,
                description = binding.addTaskDescription.text,
                date = binding.addTaskDate.text,
                time = binding.addTaskTime.text
            )
            TaskDataSource.insertTask(task)
            finish()
        }
    }
}
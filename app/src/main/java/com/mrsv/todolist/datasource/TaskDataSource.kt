package com.mrsv.todolist.datasource

import com.mrsv.todolist.model.Task

object TaskDataSource {
    private val list = arrayListOf<Task>()

    fun getList() = list.toList()

    fun insertTask(task: Task) {

        when {
            task.id == 0 -> list.add(task.copy(id = list.size + 1))
            else         -> {
                            list.remove(task)
                            list.add(task) }
        }
    }

    fun findById(taskId: Int) = list.find { it.id == taskId }
}

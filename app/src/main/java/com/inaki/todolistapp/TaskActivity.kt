package com.inaki.todolistapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import com.inaki.todolistapp.databinding.ActivityTaskBinding

class TaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        binding.saveTaskBtn.setOnClickListener {
            // here we check for empty fields
            if (checkEmptyValues()) {
                // if empty we prompt a toast
                Toast.makeText(baseContext, "Please add a new task", Toast.LENGTH_LONG).show()
            } else {
                // here we are answering to the previous activity
                setResult(RESULT_OK, setData())

                // finish the activity and goes back to the main activity
                finish()
            }
        }
    }

    /**
     * This method is going to check if any entry is empty
     * @return false when both strings are not empty
     */
    private fun checkEmptyValues(): Boolean {
        // we get the values entered for task and category
        val task = binding.titleTask.editText?.text.toString()
        val category = binding.catTask.editText?.text.toString()

        return task.isEmpty() || category.isEmpty()
    }

    private fun setData(): Intent {
        // getting the data from edit text
        val task = binding.titleTask.editText?.text.toString()
        val category = binding.catTask.editText?.text.toString()

        // constructing our task object to be sent to the previous activity
        val newTask = PojoTodo(task, category)

        // we return the intent with the data inside
        return Intent().putExtra(NEW_TASK_DATA, newTask)
    }

    companion object {
        const val NEW_TASK_DATA = "NEW_TASK_DATA"
    }
}
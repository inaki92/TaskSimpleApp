package com.inaki.todolistapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.inaki.todolistapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // property to store the adapter
    private lateinit var taskAdapter: TaskAdapter

    // here we are setting the click listener to be used by any button
    private val addingTask = View.OnClickListener {
        Intent(baseContext, TaskActivity::class.java).apply {
            startActivityForResult(this, TASK_REQUEST_CODE)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // assigning the adapter
        taskAdapter = TaskAdapter()

        // setting our click listener
        binding.addTaskBtn.setOnClickListener(addingTask)
    }

    override fun onResume() {
        super.onResume()

        binding.todoRecycler.apply {
            adapter = taskAdapter
            layoutManager = GridLayoutManager(baseContext, 3)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // here we check for the request code
        // check for result code
        // check for data not null

        if (requestCode == TASK_REQUEST_CODE
            && resultCode == RESULT_OK
            && data != null) {

                // here we get the new task inside the intent coming from the task activity
            val newTask = data.getParcelableExtra<PojoTodo>(TaskActivity.NEW_TASK_DATA)

            // setting the new data to the adapter
            newTask?.let {
                // only if it is not null we go and update the data set
                taskAdapter.updateDataSet(it)
            }

        } else {
            Toast.makeText(baseContext, "No task added", Toast.LENGTH_LONG).show()
        }

    }

    companion object {
        private const val TASK_REQUEST_CODE = 21
    }
}
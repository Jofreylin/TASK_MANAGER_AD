package com.byjofrey.tareasapp

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : ComponentActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TaskAdapter
    private lateinit var tasks: MutableList<Task>

    private val TASK_REPO_NAME: String = "MisTareas";
    private val TASK_REPO_SECTION_NAME: String = "tareas";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        val editName = findViewById<EditText>(R.id.editName)
        val editDescription = findViewById<EditText>(R.id.editDescription)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        tasks = cargarTareas()
        /* mutableListOf(
                   Task("Estudiar Kotlin", "Aprender conceptos básicos", false),
                   Task("Pagar la luz", "Vence mañana", true)
               ) */

        adapter = TaskAdapter(
            tasks,
            onDelete = { position ->
                tasks.removeAt(position)
                adapter.notifyItemRemoved(position)
                guardarTareas()
            },
            onEdit = { position ->
                mostrarDialogoEditar(position)
            },
            onToggleStatus = { position ->
                tasks[position].isCompleted = !tasks[position].isCompleted
                adapter.notifyItemChanged(position)
                guardarTareas()
            }
        )


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        btnAdd.setOnClickListener {
            val name = editName.text.toString().trim()
            val desc = editDescription.text.toString().trim()

            if (name.isNotEmpty()) {
                val newTask = Task(name, desc, false)
                tasks.add(newTask)
                adapter.notifyItemInserted(tasks.size - 1)
                guardarTareas()

                editName.text.clear()
                editDescription.text.clear()
                recyclerView.scrollToPosition(tasks.size - 1)
            }
        }
    }

    private fun guardarTareas() {
        val sharedPref = getSharedPreferences(TASK_REPO_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        val gson = Gson()
        val json = gson.toJson(tasks)
        editor.putString(TASK_REPO_SECTION_NAME, json)
        editor.apply()
    }

    private fun cargarTareas(): MutableList<Task> {
        val sharedPref = getSharedPreferences(TASK_REPO_NAME, Context.MODE_PRIVATE)
        val json = sharedPref.getString(TASK_REPO_SECTION_NAME, null)
        val gson = Gson()
        val type = object : TypeToken<MutableList<Task>>() {}
        return if (json != null) gson.fromJson(json, type) else mutableListOf()
    }


    private fun mostrarDialogoEditar(position: Int) {
        val task = tasks[position]

        val inputName = EditText(this)
        inputName.setText(task.name)
        val inputDesc = EditText(this)
        inputDesc.setText(task.description)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(50, 40, 50, 10)
        layout.addView(inputName)
        layout.addView(inputDesc)

        AlertDialog.Builder(this)
            .setTitle("Editar tarea")
            .setView(layout)
            .setPositiveButton("Guardar") { _, _ ->
                val newName = inputName.text.toString().trim()
                val newDesc = inputDesc.text.toString().trim()
                if (newName.isNotEmpty()) {
                    task.name = newName
                    task.description = newDesc
                    adapter.notifyItemChanged(position)
                    guardarTareas()
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

}

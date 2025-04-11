package com.byjofrey.tareasapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val tasks: MutableList<Task>,
                  private val onDelete: (Int) -> Unit,
                  private val onEdit: (Int) -> Unit,
                  private val onToggleStatus: (Int) -> Unit) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val txtDescription: TextView = itemView.findViewById(R.id.txtDescription)
        val btnDelete: Button = itemView.findViewById(R.id.btnDelete)
        val switchStatus: Switch = itemView.findViewById(R.id.switchStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.txtName.text = task.name
        holder.txtDescription.text = task.description

        holder.switchStatus.setOnCheckedChangeListener(null)
        holder.switchStatus.isChecked = task.isCompleted

        // Cambia color del fondo según estado
        holder.itemView.setBackgroundColor(
            if (task.isCompleted) Color.parseColor("#D0F0C0") // verde claro
            else Color.parseColor("#FFE4B2") // naranja claro
        )

        holder.btnDelete.setOnClickListener {
            val currentPosition = holder.adapterPosition
            if (currentPosition != RecyclerView.NO_POSITION) {
                onDelete(currentPosition)
            }
        }

        holder.itemView.setOnClickListener {
            val currentPosition = holder.adapterPosition
            if (currentPosition != RecyclerView.NO_POSITION) {
                onEdit(currentPosition)
            }
        }

        holder.switchStatus.setOnCheckedChangeListener { _, isChecked ->
            val currentPosition = holder.adapterPosition
            if (currentPosition != RecyclerView.NO_POSITION) {
                onToggleStatus(currentPosition)
            }
        }

    }

    override fun getItemCount(): Int = tasks.size
}

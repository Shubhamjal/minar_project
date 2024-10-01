package com.example.collegeguide.Todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.collegeguide.R

class Todo_RecyclerAdapter(val list: ArrayList<TodoEntity>, val click: TodoFragment):
RecyclerView.Adapter<Todo_RecyclerAdapter.ViewHolder>(){
    class ViewHolder(var view:View):RecyclerView.ViewHolder(view) {
        var entered_Task=view.findViewById<TextView>(R.id.EnteredTask)
        var check=view.findViewById<CheckBox>(R.id.checkBox)
        var deletetodo=view.findViewById<ImageView>(R.id.delete_btn_todo)
        var taskview=view.findViewById<View>(R.id.task)
    }

    interface ClickInterface {
        fun updateTodo(position: Int)
        fun deleteTodo(position: Int)
        fun checkTodo(checkBox: CheckBox,position: Int)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Todo_RecyclerAdapter.ViewHolder {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.base_todo_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Todo_RecyclerAdapter.ViewHolder, position: Int) {

        holder.apply {
            if(list[position].isCompleted==true){
                check.isChecked= true
            }else{
                check.isChecked= false
            }
            entered_Task.setText(list[position].Title)
            taskview.setOnClickListener{
                click.updateTodo(position)
            }
            check.setOnClickListener{
                click.checkTodo(check,position)
            }

            deletetodo.setOnClickListener{
                click.deleteTodo(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
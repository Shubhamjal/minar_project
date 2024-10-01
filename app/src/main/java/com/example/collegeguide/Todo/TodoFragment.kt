package com.example.collegeguide.Todo

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.collegeguide.R
import com.example.collegeguide.databinding.FragmentTodoBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TodoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TodoFragment : Fragment(),Todo_RecyclerAdapter.ClickInterface {

    lateinit var binding: FragmentTodoBinding
    lateinit var todoDataBase: TodoDataBase
    lateinit var todoRecycleradapter: Todo_RecyclerAdapter
    var todoList= arrayListOf<TodoEntity>()


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentTodoBinding.inflate(layoutInflater)
        return binding.root

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        todoDataBase= TodoDataBase.getInstance(requireContext())
        todoRecycleradapter = Todo_RecyclerAdapter(todoList,this)
        binding.TodoList.layoutManager= LinearLayoutManager(requireContext())
        binding.TodoList.adapter=todoRecycleradapter

        getData()//get the data from data base when application started
        binding.FabAddTOdo.setOnClickListener{
            val dialog= Dialog(requireContext())
            dialog.setContentView(R.layout.add_dialogbox_todo)

            //make dialogbox open fully
            val window = dialog.window
            val layoutParams=window?.attributes
            layoutParams?.width = ViewGroup.LayoutParams.MATCH_PARENT
            layoutParams?.height = ViewGroup.LayoutParams.WRAP_CONTENT
            window?.attributes = layoutParams

            val etTitle=dialog.findViewById<EditText>(R.id.etTitle)
            val add_btn=dialog.findViewById<Button>(R.id.Add_btn)

            dialog.show()

            add_btn.setOnClickListener {
                if(etTitle.text.toString().isEmpty()){
                    etTitle.error = "Enter Title"
                }else {
                    todoDataBase.todoInterface().insertTodo(TodoEntity(Title =etTitle.text.toString()))
                    dialog.dismiss()
                    getData()
                }
            }
            dialog.show()
        }

    }
    fun getData() {
        todoList.clear()
        todoList.addAll(todoDataBase.todoInterface().getList())
        todoRecycleradapter.notifyDataSetChanged()
    }

    override fun updateTodo(position:Int){
        val dialog= Dialog(requireContext())
        dialog.setContentView(R.layout.add_dialogbox_todo)

        //make dialogbox open fully
        val window = dialog.window
        val layoutParams=window?.attributes
        layoutParams?.width = ViewGroup.LayoutParams.MATCH_PARENT
        layoutParams?.height = ViewGroup.LayoutParams.WRAP_CONTENT
        window?.attributes = layoutParams

        val etTitle=dialog.findViewById<EditText>(R.id.etTitle)
        val add_btn=dialog.findViewById<Button>(R.id.Add_btn)
        if (position>-1){
            add_btn.setText("Update")
        }
        dialog.show()

        add_btn.setOnClickListener {
            if(etTitle.text.toString().isEmpty()){
                etTitle.error = "Enter Title"
            }else {
                todoDataBase.todoInterface().updateTodoEntity(
                    TodoEntity(
                        id = todoList[position].id,
                        Title =etTitle.text.toString())
                )
                dialog.dismiss()
                getData()
            }
        }
        dialog.show()


    }
    override fun deleteTodo(position: Int){
        todoDataBase.todoInterface().deleteTodoEntity(todoList[position])
        getData()
        todoRecycleradapter.notifyDataSetChanged()
    }
    override fun checkTodo(checkBox: CheckBox,position: Int){
        var alertDialog = android.app.AlertDialog.Builder(context)
        alertDialog.setTitle("have_you_completed_your_task")
        alertDialog.setPositiveButton("yes"){_,_ ->
            todoList[position].isCompleted = true
            todoDataBase.todoInterface().updateTodoEntity(todoList[position])
            checkBox.isChecked=true
            getData()
        }
        alertDialog.setNegativeButton("no"){_,_ ->
            todoList[position].isCompleted = false
            todoDataBase.todoInterface().updateTodoEntity(todoList[position])
            checkBox.isChecked=false
            getData()
        }
        alertDialog.show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TodoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TodoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



}
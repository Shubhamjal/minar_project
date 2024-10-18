package com.example.collegeguide.AcademicRecord

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.collegeguide.R
import com.example.collegeguide.databinding.FragmentAcademicBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AcademicFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AcademicFragment : Fragment() {

    lateinit var binding: FragmentAcademicBinding
    var academicList= arrayListOf<AcademicEntity>()
    lateinit var academicDatabase: AcademicDatabase

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

        binding=FragmentAcademicBinding.inflate(layoutInflater)
        return binding.root

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_academic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        academicDatabase= AcademicDatabase.getInstance(requireContext())


        binding.btnSemester1Details.setOnClickListener {
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.sem1_academic_record)

            //to fit dialogbox correctly
            val window = dialog.window
            val layoutParams = window?.attributes
            layoutParams?.width = ViewGroup.LayoutParams.MATCH_PARENT
            layoutParams?.height = ViewGroup.LayoutParams.WRAP_CONTENT
            window?.attributes = layoutParams

            dialog.show()
        }
    }







    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AcademicFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AcademicFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}
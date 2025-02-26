package com.example.collegeguide.StudyMaterial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.collegeguide.R
import com.example.collegeguide.databinding.FragmentStudyMaterialBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StudyMaterialFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudyMaterialFragment : Fragment() {

    lateinit var binding: FragmentStudyMaterialBinding
    private lateinit var pdfAdapter: PdfAdapter
    private lateinit var pdfFiles: Array<String>

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

        binding = FragmentStudyMaterialBinding.inflate(layoutInflater)
        return binding.root

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_study_material, container, false)

        // Initialize RecyclerView
        pdfFiles = getPdfFilesFromAssets()
        pdfAdapter = PdfAdapter(pdfFiles) { selectedPdf ->
            // Handle PDF click, open PdfViewerFragment or show PDF in a viewer
            openPdfViewer(selectedPdf)
        }
        binding.pdfRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pdfAdapter
        }

        return binding.root

    }

    // Get PDF files from the assets folder
    private fun getPdfFilesFromAssets(): Array<String> {
        return context?.assets?.list("")?.filter { it.endsWith(".pdf") }?.toTypedArray() ?: arrayOf()
    }

    // Function to open PDF in a viewer fragment
    private fun openPdfViewer(pdfFileName: String) {
        val fragment = PdfViewerFragment.newInstance(pdfFileName)
        fragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }







    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StudyMaterialFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StudyMaterialFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.collegeguide.R

class PdfViewerFragment : Fragment() {

    private lateinit var pdfView: PdfView
    private var fileName: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pdf_viewer, container, false)
        pdfView = view.findViewById(R.id.pdfView)

        fileName = arguments?.getString("file_name")
        fileName?.let {
            pdfView.fromAsset(it).load()
        }

        return view
    }

    companion object {
        fun newInstance(fileName: String): PdfViewerFragment {
            val fragment = PdfViewerFragment()
            val args = Bundle()
            args.putString("file_name", fileName)
            fragment.arguments = args
            return fragment
        }
    }
}

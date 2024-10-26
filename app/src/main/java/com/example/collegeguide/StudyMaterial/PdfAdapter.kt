package com.example.collegeguide.StudyMaterial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PdfAdapter(private val pdfList: Array<String>, private val onClick: (String) -> Unit) :
    RecyclerView.Adapter<PdfAdapter.PdfViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PdfViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return PdfViewHolder(view)
    }

    override fun onBindViewHolder(holder: PdfViewHolder, position: Int) {
        val pdfName = pdfList[position]
        holder.bind(pdfName)
        holder.itemView.setOnClickListener { onClick(pdfName) }
    }

    override fun getItemCount(): Int = pdfList.size

    class PdfViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(android.R.id.text1)
        fun bind(pdfName: String) {
            textView.text = pdfName
        }
    }
}

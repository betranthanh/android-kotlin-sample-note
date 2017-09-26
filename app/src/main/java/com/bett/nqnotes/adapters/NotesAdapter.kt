package com.bett.nqnotes.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bett.nqnotes.R
import com.bett.nqnotes.models.NoteDto
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.*

/**
 * Created by bett on 9/20/17.
 */
class NotesAdapter : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    var items: List<NoteDto> = ArrayList()

    private val clickSubject = PublishSubject.create<NoteDto>()
    val clickEvent: Observable<NoteDto> = clickSubject

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(items[position])

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.note_row, parent, false)

        return ViewHolder(itemView)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtTitle: TextView? = null
        var txtContent: TextView? = null

        init {
            this.txtTitle = itemView.findViewById<TextView>(R.id.txtTitle)
            this.txtContent = itemView.findViewById<TextView>(R.id.txtContent)

            itemView.setOnClickListener {
                clickSubject.onNext(items[layoutPosition])
            }
        }
        fun bind(note: NoteDto) {
            txtTitle?.text = note.title
            txtContent?.text = note.content
        }

    }


}
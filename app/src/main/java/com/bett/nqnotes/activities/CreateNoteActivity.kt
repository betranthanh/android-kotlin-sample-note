package com.bett.nqnotes.activities

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import butterknife.BindView
import butterknife.OnClick
import com.bett.nqnotes.R
import com.bett.nqnotes.bus.NoteEvent
import com.bett.nqnotes.realm.NotesRealmManager
import com.bett.nqnotes.utils.Constants
import com.squareup.otto.Bus
import javax.inject.Inject

class CreateNoteActivity : BaseActivity() {

    @LayoutRes
    override fun getLayoutResId(): Int {
        return R.layout.activity_create_note
    }

    @BindView(R.id.editTextTitle) lateinit var editTextTitle: EditText
    @BindView(R.id.editTextContent) lateinit var editTextContent: EditText
    @BindView(R.id.btnDelete) lateinit var btnDelete: ImageView

    @Inject lateinit var notesRealmManager: NotesRealmManager
    @Inject lateinit var bus: Bus

    var noteId = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent().inject(this);

        val bundle = intent.extras
        if (bundle != null) {
            noteId = bundle.getLong(Constants.INTENT_KEY_NOTE_ID)
        }

        if (noteId == -1L) {
            supportActionBar?.title = "Create New"
            btnDelete.visibility = View.GONE
        } else {
            supportActionBar?.title = "Edit"
            btnDelete.visibility = View.VISIBLE
            var noteDto = notesRealmManager.find(noteId)
            editTextTitle.setText(noteDto?.title)
            editTextContent.setText(noteDto?.content)
        }
    }

    @OnClick(R.id.btnBack)
    fun onClickBtnBack() {
        finish()
    }

    @OnClick(R.id.btnDelete)
    fun onClickBtnDelete() {
        notesRealmManager.deleteById(noteId)
        bus.post(NoteEvent(NoteEvent.ACTION_DELETE))

        finish()
    }

    @OnClick(R.id.btnSave)
    fun onClickBtnSave() {
        if (noteId == -1L) {
            notesRealmManager.insert(editTextTitle.text.toString(), editTextContent.text.toString())
            bus.post(NoteEvent(NoteEvent.ACTION_INSERT))
        } else {
            notesRealmManager.update(noteId, editTextTitle.getText().toString(), editTextContent.text.toString())
            bus.post(NoteEvent(NoteEvent.ACTION_UPDATE))
        }

        finish()
    }
}

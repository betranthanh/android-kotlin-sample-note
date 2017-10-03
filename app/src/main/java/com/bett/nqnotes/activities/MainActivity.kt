package com.bett.nqnotes.activities

import android.content.Intent
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import butterknife.OnClick
import com.bett.nqnotes.R
import com.bett.nqnotes.adapters.NotesAdapter
import com.bett.nqnotes.bus.NoteEvent
import com.bett.nqnotes.models.NoteDto
import com.bett.nqnotes.realm.NotesRealmManager
import com.bett.nqnotes.utils.Constants
import com.squareup.otto.Bus
import com.squareup.otto.Subscribe
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class MainActivity : BaseActivity() {

    @LayoutRes
    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    @BindView(R.id.recyclerView) lateinit var recyclerView: RecyclerView
    private val adapter = NotesAdapter()

    @Inject lateinit var notesRealmManager: NotesRealmManager
    @Inject lateinit var bus: Bus

    private var subscrible: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent().inject(this);

        bus.register(this)

        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()

        recyclerView.adapter = adapter
        adapter.items = notesRealmManager.findAll()
        adapter.notifyDataSetChanged()

        subscrible = adapter.clickEvent.subscribe {
            var intent = Intent(this, CreateNoteActivity::class.java)
            intent.putExtra(Constants.INTENT_KEY_NOTE_ID, it.id)
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        subscrible?.dispose()
    }

    @OnClick(R.id.btnAddNew)
    fun onClickBtnAddNew() {
        val intent = Intent(this, CreateNoteActivity::class.java)
        intent.putExtra(Constants.INTENT_KEY_NOTE_ID, -1L)
        startActivity(intent)
    }

    @OnClick(R.id.btnInfo)
    fun onClickBtnInfo() {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }

    @Subscribe
    fun onTodoEvent(event: NoteEvent) {
        adapter.items = notesRealmManager.findAll()

        adapter.notifyDataSetChanged()
    }
}

package app.ky.mysimplenoteapp

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var viewModel: NoteDBViewModel
    lateinit var adapter: NoteListAdapter
    var TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            var intent: Intent = Intent(applicationContext,NoteActivity::class.java)
            startActivityForResult(intent,0)
        }

        adapter = NoteListAdapter(this, recyclerViewClickListener)
        viewModel = ViewModelProviders.of(this).get(NoteDBViewModel::class.java)
        viewModel.getAllNote()?.observe(this,object :Observer<List<Note>>{
            override fun onChanged(notes: List<Note>?) {
                if(notes !=null){
                   adapter.setNotes(notes)
                }
            }
        })
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        var bundle = data?.extras
        var noteId = 0
        var title = ""
        var content = ""
        var simpleDateFormat:SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm")
        var dt: Date = Date()
        var dts = simpleDateFormat.format(dt)
        if(bundle!=null) {
            noteId  = bundle.getInt(Constant.KEY_ID, 0)
            title  = bundle.getString(Constant.KEY_TITLE)
            content = bundle.getString(Constant.KEY_CONTENT)
        }else{
            return
        }
        if (resultCode == Constant.ACTION_SAVE){ // NEW Note
            Log.e(TAG,"ADD new note")
            var node = Note(title,content,dts)
            viewModel.insert(node)
        }else if (resultCode == Constant.ACTION_MODIFY){
            Log.e(TAG,"update new note")
            var node = Note(title,content,dts)
            node.setId(noteId)
            viewModel.update(node)
        }else if(resultCode == Constant.ACTION_DELETE){
            Log.e(TAG,"deltet note")
            var node = Note(title,content,dts)
            node.setId(noteId)
            viewModel.delete(node)
        }
    }

    var recyclerViewClickListener:RecyclerViewClickListener = object :RecyclerViewClickListener{
        override fun recyclerViewerListClicked(v: View, position: Int) {
            Log.e(TAG,position.toString())
            var intent: Intent = Intent(applicationContext,NoteActivity::class.java)
            var bundle = Bundle()

            bundle.putInt(Constant.KEY_ID, adapter.mNotes!![position].getId())
            bundle.putString(Constant.KEY_TITLE, adapter.mNotes!![position].getTitle())
            bundle.putString(Constant.KEY_CONTENT, adapter.mNotes!![position].getContent())

            intent.putExtras(bundle)
            startActivityForResult(intent,0)
        }

    }
}

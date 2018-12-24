package app.ky.mysimplenoteapp

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Room
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.util.Log

class NoteDBRepo(var application: Application){
    var db:NoteDB
    var dao:NoteDao
    var TAG = "NoteDBRepo"
    init {
        db = Room
            .databaseBuilder(application,NoteDB::class.java,"note_database")
            .build()
        dao = db.noteDao()
    }
    fun insert(note:Note){
        insertAsyncTask(dao).execute(note)
    }

    fun getAllNotes():LiveData<List<Note>>{
        return dao.getAllNotes()
    }
    fun updateNote(note:Note){
        updateAsyncTask(dao).execute(note)
    }

    inner class insertAsyncTask(dao:NoteDao) : AsyncTask<Note,Void,Void>(){
        override fun doInBackground(vararg parms: Note): Void? {
            if(parms!= null && parms!![0]!=null) {
                Log.e(TAG,"ADD new note")
                dao.insert(parms[0])
            }
            return null
        }

    }

    inner class updateAsyncTask(dao:NoteDao) : AsyncTask<Note,Void,Void>(){
        override fun doInBackground(vararg parms: Note): Void? {
            if(parms!= null && parms!![0]!=null) {
                Log.e(TAG,"updateNote new note")
                dao.updateNote(parms[0])
            }
            return null
        }

    }
}
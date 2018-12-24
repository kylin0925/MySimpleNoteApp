package app.ky.mysimplenoteapp

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

class NoteDBViewModel(application: Application) :AndroidViewModel(application){
    private var repo = NoteDBRepo(application)
    private var mAllLiveNote: LiveData<List<Note>>?

    init {
         mAllLiveNote = repo.getAllNotes()
    }

    fun getAllNote():LiveData<List<Note>>?{
        return this.mAllLiveNote
    }

    fun insert(note:Note){
        repo.insert(note)
    }

    fun update(note:Note){
        repo.updateNote(note)
    }
}
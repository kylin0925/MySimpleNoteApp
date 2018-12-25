package app.ky.mysimplenoteapp

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface NoteDao {

    @Insert
    fun insert(note:Note)

    @Query("select * from NoteTable order by id asc")
    fun getAllNotes() :LiveData<List<Note>>

    @Update
    fun updateNote(note:Note)

    @Delete
    fun deleteNote(note:Note)
}
package app.ky.mysimplenoteapp

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface NoteDao {

    @Insert
    fun insert(note:Note)

    @Query("select * from NoteTable order by id asc")
    fun getAllNotes() :LiveData<List<Note>>

    @Update
    fun updateNote(note:Note)
}
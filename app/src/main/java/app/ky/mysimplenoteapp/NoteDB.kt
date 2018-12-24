package app.ky.mysimplenoteapp

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(Note::class), version = 1)
abstract class NoteDB : RoomDatabase() {
    abstract fun noteDao():NoteDao
}
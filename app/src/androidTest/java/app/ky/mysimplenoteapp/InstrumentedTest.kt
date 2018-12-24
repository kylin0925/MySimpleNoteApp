package app.ky.mysimplenoteapp

import android.app.Application
import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import android.arch.persistence.room.Room
import android.util.Log
import org.junit.Assert


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class InstrumentedTest {
    lateinit var repo: NoteDBRepo

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        repo = NoteDBRepo(context.applicationContext as Application)

    }


//    @Test
//    fun dbTest(){
//        repo.insert(Note("abc","def","2018/1/1 00:0:00"))
//        var notes = repo.getAllNotes()
//        Log.e("tag",notes.size.toString())
//        var m = Note("test","test","2018/1/1 00:0:00")
//        m.setId(2)
//        repo.updateNote(m)
//        notes = repo.getAllNotes()
//        assertTrue(notes[1].getTitle() == "test")
//    }
}

package app.ky.mysimplenoteapp

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import android.R.string.ok
import android.R.string.ok





class NoteActivity : AppCompatActivity() {
    val TAG = "NoteActivity"
    lateinit var edtTitle: EditText
    lateinit var edtContent: EditText

    var noteId: Int = 0
    lateinit var title: String
    lateinit var content: String
    var resultCode = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        edtTitle = findViewById(R.id.edtTitle)
        edtContent = findViewById(R.id.edtContent)

        var bundle = intent.extras
        if(bundle!=null) {
            noteId  = bundle.getInt("ID", 0)
            title  = bundle.getString("TITLE")
            content = bundle.getString("CONTENT")
            edtTitle.setText(title)
            edtContent.setText(content)
            resultCode = Constant.ACTION_MODIFY
            Log.d(TAG,"id " + noteId.toString() + " title: " + title + " content: " + content)
        }else{
            Log.d(TAG,"NEW Note")
            resultCode = Constant.ACTION_SAVE
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_note, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_save -> {
                var intent = Intent()
                var bundle = Bundle()
                bundle.putInt(Constant.KEY_ID,noteId)
                bundle.putString(Constant.KEY_TITLE, edtTitle.text.toString())
                bundle.putString(Constant.KEY_CONTENT, edtContent.text.toString())
                intent.putExtras(bundle)
                this.setResult(this.resultCode, intent)
                finish()
                return true
            }
            R.id.action_delete ->{
                var alertDialog = AlertDialog.Builder(this)
                    .setMessage("abcd")
                    .setPositiveButton("DELETE") { dialog, which ->
                        Log.e(TAG,"ok")
                        var intent = Intent()
                        var bundle = Bundle()
                        this.resultCode = Constant.ACTION_DELETE
                        bundle.putInt(Constant.KEY_ID,noteId)
                        bundle.putString(Constant.KEY_TITLE, edtTitle.text.toString())
                        bundle.putString(Constant.KEY_CONTENT, edtContent.text.toString())
                        intent.putExtras(bundle)
                        this.setResult(this.resultCode, intent)
                        finish()
                    }
                    .setNegativeButton("CANCEL") { dialog, which ->
                        Log.e(TAG,"CANCEL")
                    }
                alertDialog.show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

package app.ky.mysimplenoteapp

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull


@Entity(tableName = "NoteTable")
class Note(@NonNull title:String, @NonNull content:String ,@NonNull date:String) {
    @PrimaryKey(autoGenerate = true)
    private var id:Int = 0

    @NonNull
    @ColumnInfo(name="title")
    private var mTitle:String ?=null

    @NonNull
    @ColumnInfo(name="content")
    private var mContent:String ?=null

    @NonNull
    @ColumnInfo(name="date")
    private var mDate:String ?=null

    init {
        this.mTitle = title
        this.mContent = content
        this.mDate = date
    }


    fun getId():Int{
        return this.id
    }
    fun setId(id:Int){
        this.id = id
    }

    fun getTitle():String?{
        return this.mTitle
    }
    fun setTitle(title:String){
        this.mTitle =title
    }

    fun getContent():String?{
        return this.mContent
    }
    fun setContent(content: String){
        this.mContent = content
    }

    fun getDate():String?{
        return this.mDate
    }
    fun setDate(date: String){
        this.mDate = date
    }
}
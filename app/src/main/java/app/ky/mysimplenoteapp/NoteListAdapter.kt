package app.ky.mysimplenoteapp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class NoteListAdapter(context:Context,recyclerViewClickListener: RecyclerViewClickListener):
    RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>(){
    var recyclerViewClickListener: RecyclerViewClickListener
    var mNotes : List<Note>? = null
    private var layoutInflater: LayoutInflater
    init {
        layoutInflater = LayoutInflater.from(context)
        this.recyclerViewClickListener = recyclerViewClickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        var itemView = layoutInflater.inflate(R.layout.list_item,parent,false)
        return NoteViewHolder(itemView, this)
    }

    override fun getItemCount(): Int {
        return if (mNotes == null) 0 else mNotes!!.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        if (mNotes != null){
            var current = mNotes!![position]
            holder.tvTitle.text = current.getTitle()
            holder.tvDate.text = current.getDate()
        }
        //else nothing
    }
    fun setNotes(notes:List<Note>){
        this.mNotes = notes
        notifyDataSetChanged()
    }
    inner class NoteViewHolder(itemView: View,adapter: NoteListAdapter):
        RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var tvTitle:TextView
        var tvDate:TextView
        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvDate = itemView.findViewById(R.id.tvDate)
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            Log.e("click",layoutPosition.toString())
            recyclerViewClickListener.recyclerViewerListClicked(itemView,layoutPosition)
        }
    }
}
package app.ky.mysimplenoteapp

import android.view.View

interface RecyclerViewClickListener {
    fun recyclerViewerListClicked(v: View, position: Int)
}
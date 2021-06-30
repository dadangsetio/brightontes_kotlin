package com.repairzone.brightontes.ui.widget

import android.util.Log
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.RecyclerView

class ObservableListChangeCallback <T> (
    private val adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>? = null
) : ObservableList.OnListChangedCallback<ObservableList<T>>() {
    override fun onChanged(sender: ObservableList<T>?) {
        adapter?.notifyDataSetChanged()
    }

    override fun onItemRangeChanged(
        sender: ObservableList<T>?,
        positionStart: Int,
        itemCount: Int
    ) {
        adapter?.notifyItemRangeChanged(positionStart, itemCount)
    }

    override fun onItemRangeInserted(
        sender: ObservableList<T>?,
        positionStart: Int,
        itemCount: Int
    ) {
        adapter?.notifyItemRangeInserted(positionStart, itemCount)
    }

    override fun onItemRangeMoved(
        sender: ObservableList<T>?,
        fromPosition: Int,
        toPosition: Int,
        itemCount: Int
    ) {
        adapter?.notifyItemRangeChanged(fromPosition, itemCount)
    }

    override fun onItemRangeRemoved(
        sender: ObservableList<T>?,
        positionStart: Int,
        itemCount: Int
    ) {
        adapter?.notifyItemRangeRemoved(positionStart, itemCount)
    }
}
package com.demoss.idp.util.recycler

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SwipeItemInAnyDirectionCallback(
    private val onItemSwipeLeft: ((Int) -> Unit)? = null,
    private val onItemSwipeRight: ((Int) -> Unit)? = null
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    fun constructor() {

    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        when (direction) {
            ItemTouchHelper.RIGHT -> onItemSwipeRight?.invoke(viewHolder.adapterPosition)
            ItemTouchHelper.LEFT -> onItemSwipeLeft?.invoke(viewHolder.adapterPosition)
        }
    }
}
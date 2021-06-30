package com.repairzone.brightontes.ui.widget

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.repairzone.brightontes.BR

class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root){

    fun <T> bind(item: T){
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }
}
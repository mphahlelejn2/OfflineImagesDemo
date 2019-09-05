package com.jeff.offlineimagesdemo.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jeff.offlineimagesdemo.repo.Repository

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    companion object {
        fun getInstance(repository: Repository)= ViewModelFactory(repository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemListViewModel::class.java!!)) {
            return ItemListViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(ItemDetailsViewModel::class.java!!)) {
            return ItemDetailsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
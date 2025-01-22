package com.ppm.inventstuff.ui.room

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ppm.inventstuff.data.InventoryRepository
import com.ppm.inventstuff.data.local.ItemsRoom
import kotlinx.coroutines.launch

class InventoryItemViewModel(private val repository: InventoryRepository) : ViewModel() {

    private val _currentImageUri = MutableLiveData<Uri?>()
    val currentImageUri: LiveData<Uri?> get() = _currentImageUri

    fun setCurrentImageUri(uri: Uri?) {
        _currentImageUri.value = uri
    }

    fun getAllInventoryItem(roomId: Int) : LiveData<List<ItemsRoom>> =
        repository.getAllInventoryItem(roomId)

    fun insertInventoryItem(itemsRoom: ItemsRoom) {
        viewModelScope.launch {
            repository.insertInventoryItem(itemsRoom)
        }
    }

}
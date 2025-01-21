package com.ppm.inventstuff.ui.room

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ppm.inventstuff.data.InventoryRepository
import com.ppm.inventstuff.data.local.Room
import kotlinx.coroutines.launch

class RoomViewModel(private val repository: InventoryRepository) : ViewModel() {

    private val _currentImageUri = MutableLiveData<Uri?>()
    val currentImageUri: LiveData<Uri?> get() = _currentImageUri

    fun setCurrentImageUri(uri: Uri?) {
        _currentImageUri.value = uri
    }

    fun getAllRoom(): LiveData<List<Room>> = repository.getAllRoom()
    fun insertRoom(room: Room) {
        viewModelScope.launch {
            Log.d("RoomViewModel", "Inserting room: $room")
            repository.insertRoom(room)
        }
    }
}
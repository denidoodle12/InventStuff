package com.ppm.inventstuff.ui.recapitulation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ppm.inventstuff.data.InventoryRepository
import com.ppm.inventstuff.data.local.RecapitulationResult

class RecapitulationViewModel(private val repository: InventoryRepository) : ViewModel() {
    fun getTotalRecapitulation(): LiveData<RecapitulationResult> {
        return repository.calculateRecapitulation()
    }
}
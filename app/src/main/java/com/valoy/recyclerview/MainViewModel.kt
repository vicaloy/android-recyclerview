package com.valoy.recyclerview

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState(items = populateData()))
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    private fun populateData(): List<Item> {
        val dataset = mutableListOf<Item>()

        repeat(20) {
            dataset.add(Item("Item $it", "This is the description for item $it."))
        }

        return dataset
    }
}
package com.example.hobbymatchmakingapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hobbymatchmakingapp.presentation.util.Hobby
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {
    private val _hobbies = MutableStateFlow<List<Hobby>>(emptyList())
    val hobbies: StateFlow<List<Hobby>> = _hobbies

    init {
        loadHobbies()
    }
    private fun loadHobbies() {
        _hobbies.value = listOf(
            Hobby(1, "Football", "Sport"),
            Hobby(2, "Gaming", "Entertainment"),
            Hobby(3, "Cooking", "Lifestyle")
        )
    }
    fun addHobby(name: String, category: String) {
        val currentList = _hobbies.value.toMutableList()

        val newHobby = Hobby(
            id = currentList.size + 1,
            name = name,
            category = category
        )

        currentList.add(newHobby)
        _hobbies.value = currentList
    }
    fun updateHobby(id: Int, name: String, category: String) {
        _hobbies.value = _hobbies.value.map {
            if (it.id == id) it.copy(name = name, category = category)
            else it
        }
    }
}
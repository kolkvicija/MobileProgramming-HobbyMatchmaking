package com.example.hobbymatchmakingapp.model

import com.example.hobbymatchmakingapp.presentation.util.Hobby
object HardcodedData {
    fun getHobbies(): List<Hobby> {
        return listOf(
            Hobby(1, "Football", "Sport"),
            Hobby(2, "Gaming", "Entertainment"),
            Hobby(3, "Cooking", "Lifestyle"),
            Hobby(4, "Basketball", "Sport"),
            Hobby(5, "Reading", "Education")
        )
    }
}
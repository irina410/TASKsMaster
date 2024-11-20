package com.example.tasksmaster.repository

import android.content.Context
import com.example.tasksmaster.model.Algorithm
import com.google.gson.Gson
import java.io.File

class AlgorithmsRepository(context: Context) {
    private val file = File(context.filesDir, "algorithms.json")

    fun saveAlgorithms(algorithms: List<Algorithm>) {
        val json = Gson().toJson(algorithms)
        file.writeText(json)
    }

    fun loadAlgorithms(): List<Algorithm> {
        if (!file.exists()) return emptyList()
        return Gson().fromJson(file.readText(), Array<Algorithm>::class.java).toList()
    }
}

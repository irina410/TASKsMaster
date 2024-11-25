package com.example.tasksmaster.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tasksmaster.model.Algorithm
import com.example.tasksmaster.repository.AlgorithmsRepository

class AlgorithmsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = AlgorithmsRepository(application)

    private val _algorithms = MutableLiveData<List<Algorithm>>()
    val algorithms: LiveData<List<Algorithm>> get() = _algorithms

    init {
        loadAlgorithms()
    }

    private fun loadAlgorithms() {
        _algorithms.value = repository.loadAlgorithms()
    }

    fun addAlgorithm(algorithm: Algorithm) {
        val currentList = _algorithms.value.orEmpty().toMutableList()
        currentList.add(algorithm)
        _algorithms.value = currentList
        repository.saveAlgorithms(currentList)
    }
//    fun addAlgorithm(name: String, subtasks: List<Pair<String, Long>>) {
//
//        val totalDuration = subtasks.sumOf { it.second } // Суммируем время всех подзадач
//
//        val algorithm = Algorithm(name, totalDuration, subtasks) // Создаем объект
//        addAlgorithm(algorithm) // Вызываем основной метод
//    }


}
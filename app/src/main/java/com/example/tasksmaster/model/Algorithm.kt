package com.example.tasksmaster.model

data class Algorithm(
    val name: String,
    val subtasks: List<Pair<String, Long>>
)

package com.example.tasksmaster.ui.algorithms

import AlgorithmsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasksmaster.R
import com.example.tasksmaster.model.Algorithm
import com.example.tasksmaster.viewmodel.AlgorithmsViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AlgorithmsFragment : Fragment() {

    private val viewModel: AlgorithmsViewModel by viewModels()
    private lateinit var adapter: AlgorithmsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_algorithms, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val fab = view.findViewById<FloatingActionButton>(R.id.fab)

        adapter = AlgorithmsAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        // Обновляем данные в списке
        viewModel.algorithms.observe(viewLifecycleOwner) {
            adapter.setAlgorithms(it)
        }

        fab.setOnClickListener {
            val dialog = AddAlgorithmDialog { algorithmName, subtasks ->
                val algorithm = Algorithm(
                    name = algorithmName,
                    subtasks = subtasks // Это список подзадач
                )
                viewModel.addAlgorithm(algorithm) // Передаем объект Algorithm
            }
            dialog.show(parentFragmentManager, "AddAlgorithmDialog")

        }


    }
}
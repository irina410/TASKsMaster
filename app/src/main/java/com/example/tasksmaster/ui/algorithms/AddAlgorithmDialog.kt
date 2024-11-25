package com.example.tasksmaster.ui.algorithms

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import com.example.tasksmaster.R
import com.google.android.material.textfield.TextInputEditText
import toDuration

class AddAlgorithmDialog(private val onAlgorithmAdded: (String, List<Pair<String, Long>>) -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_add_algorithm, null)

        val algorithmNameEditText = view.findViewById<EditText>(R.id.algorithmNameEditText)
        val subtasksContainer = view.findViewById<LinearLayout>(R.id.subtasksContainer)
        val addSubtaskButton = view.findViewById<Button>(R.id.addSubtaskButton)
        val saveButton = view.findViewById<Button>(R.id.saveButton)
        val cancelButton = view.findViewById<Button>(R.id.cancelButton)

        // Добавляем подзадачи
        addSubtaskButton.setOnClickListener {
            val subtaskView = LayoutInflater.from(context).inflate(R.layout.item_subtask, subtasksContainer, false)
            subtasksContainer.addView(subtaskView)
        }

        // Кнопка "Сохранить"
        saveButton.setOnClickListener {
            val algorithmName = algorithmNameEditText.text.toString()
            val subtasks = mutableListOf<Pair<String, Long>>()

            for (i in 0 until subtasksContainer.childCount) {
                val subtaskView = subtasksContainer.getChildAt(i)
                val subtaskName = subtaskView.findViewById<TextInputEditText>(R.id.subtaskNameEditText).text.toString()
                val subtaskTime = subtaskView.findViewById<TextInputEditText>(R.id.subtaskTimeEditText).text.toString().toDuration()
                subtasks.add(subtaskName to subtaskTime)
            }

            if (algorithmName.isNotBlank()) {
                onAlgorithmAdded(algorithmName, subtasks)
            }
            dismiss()
        }

        // Кнопка "Отмена"
        cancelButton.setOnClickListener {
            dismiss()
        }

        dialog.setContentView(view)
        return dialog
    }
}
package com.example.tasksmaster.ui.main

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tasksmaster.ui.algorithms.AlgorithmsFragment
import com.example.tasksmaster.ui.tasks.TaskFragment

class MainPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TaskFragment()
            1 -> AlgorithmsFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}
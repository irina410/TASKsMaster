import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tasksmaster.model.Algorithm

class AlgorithmsAdapter : RecyclerView.Adapter<AlgorithmsAdapter.AlgorithmViewHolder>() {

    private val algorithms = mutableListOf<Algorithm>()

    fun setAlgorithms(newAlgorithms: List<Algorithm>) {
        algorithms.clear()
        algorithms.addAll(newAlgorithms)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlgorithmViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_2, parent, false)
        return AlgorithmViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlgorithmViewHolder, position: Int) {
        val algorithm = algorithms[position]
        holder.nameTextView.text = algorithm.name


        // Если у алгоритма нет подзадач, используем значение по умолчанию
        val totalDuration = if (algorithm.subtasks.isEmpty()) {
            0L // Общее время = 0 секунд
        } else {
            algorithm.subtasks.sumOf { subtask ->
                algorithm.subtasks[position].second
            }
        }

        // Отображаем общее время (в формате чч:мм:сс)
        holder.timeTextView.text = "Общее время: ${totalDuration.toReadableString()}"
//        // Обработчик клика
//        holder.itemView.setOnClickListener {
//            onItemClick(algorithm)  // Передаем выбранный алгоритм
//        }
    }


    override fun getItemCount() = algorithms.size

    class AlgorithmViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(android.R.id.text1)
        val timeTextView: TextView = view.findViewById(android.R.id.text2)
    }
}

// Extensions для работы с временем
fun String?.toDuration(): Long {
    if (this.isNullOrEmpty()) return 0L // Если время не указано, возвращаем 0 секунд
    val parts = this.split(":")
    require(parts.size == 3) { "Invalid time format. Expected format: hh:mm:ss" }
    return parts[0].toLong() * 3600L + parts[1].toLong() * 60L + parts[2].toLong()
}


fun Long.toReadableString(): String {
    val hours = this / 3600
    val minutes = (this % 3600) / 60
    val seconds = this % 60
    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}

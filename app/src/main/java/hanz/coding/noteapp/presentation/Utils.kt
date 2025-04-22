package hanz.coding.noteapp.presentation

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun getTime(i: Int): String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.MINUTE, -i)

    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return formatter.format(calendar.time)
}

fun getCurrentTime(): String {
    val calendar = Calendar.getInstance()

    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return formatter.format(calendar.time)
}

fun getRandomColor(): Int {
    return softColorPalette.random()
}

val softColorPalette: List<Int> = listOf(
    0xFFB3E5FC.toInt(),
    0xFFCFD8DC.toInt(),
    0xFFFFE0B2.toInt(),
    0xFFFFF9C4.toInt(),
    0xFFE1BEE7.toInt()
)
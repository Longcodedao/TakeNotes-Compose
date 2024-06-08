package com.example.takenotes.model

import java.text.SimpleDateFormat
import java.util.Date

object FakeNotes {

    val list: List<Note> = listOf(
        Note(
            title = "Meeting Notes",
            details = "Discuss the quarterly budget report and updates on the project status.",
            dateCreated = SimpleDateFormat("yyyy-MM-dd").parse("2024-06-08") ?: Date()
        ),
        Note(
            title = "Grocery List",
            details = "Milk, Eggs, Flour, Sugar, Salt, Apples",
            dateCreated = SimpleDateFormat("yyyy-MM-dd").parse("2024-06-07") ?: Date()
        ),
        Note(
            title = "Book Recommendations",
            details = "1. 'The Catcher in the Rye' by J.D. Salinger\n2. '1984' by George Orwell",
            dateCreated = SimpleDateFormat("yyyy-MM-dd").parse("2024-06-06") ?: Date()
        )
    )
}
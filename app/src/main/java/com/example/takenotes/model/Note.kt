package com.example.takenotes.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.takenotes.utils.DateConvert
import java.util.*
import java.util.UUID

@Entity(tableName = "notes")
@TypeConverters(DateConvert::class)
data class Note (
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    @ColumnInfo val title: String,
    @ColumnInfo val details: String,
    @ColumnInfo val dateCreated: Date
)
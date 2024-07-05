package com.example.listadecompras.domain.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class User(
    @PrimaryKey val uid: UUID,
)
package com.example.listadecompras.domain.DTOs

import kotlinx.serialization.Serializable

@Serializable
data class HistoryResponse (val id: String, val history: List<SavedList>)

package com.example.listadecompras.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class HistoryResponse (val id: String, val history: List<SavedList>)

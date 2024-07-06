package com.example.listadecompras.domain.DTOs

import kotlinx.serialization.Serializable

@Serializable
data class SavedList (val id: Int, val totalPrice: Double, val totalQuantity: Double, val phoneID: String, val product: List<Product>)


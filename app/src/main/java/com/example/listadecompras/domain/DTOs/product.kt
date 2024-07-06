package com.example.listadecompras.domain.DTOs

import kotlinx.serialization.Serializable

@Serializable()
data class Product(val name: String, var price: Double, val quantity: Int, val id: Int )


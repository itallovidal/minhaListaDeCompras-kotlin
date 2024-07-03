package com.example.listadecompras.domain.models

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable()
data class ProductList(val products: List<Product>?, val phoneID: String = UUID.randomUUID().toString()) {}
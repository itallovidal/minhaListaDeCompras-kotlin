package com.example.listadecompras.domain.models

import kotlinx.serialization.Serializable

@Serializable()
data class ProductList(val products: List<Product>?, val phoneID: String)
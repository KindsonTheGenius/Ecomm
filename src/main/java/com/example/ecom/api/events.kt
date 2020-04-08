package com.example.ecom.api

data class OrderCreatedEvent (
        val orderId: String,
        val price: Double,
        val number:Int,
        val productId:String
)

data class ProductAddedEvent (
        val id: String,
        val price: Double,
        val stock:Int,
        val description: String
)

data class StockUpdatedEvent(
        val id:String,
        val stock: Int
)
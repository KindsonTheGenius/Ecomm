package com.example.ecom.query

import com.example.ecom.models.Product
import org.axonframework.modelling.command.Repository
import org.springframework.data.jpa.repository.JpaRepository

class GetOrdersQuery
class GetProductsQuery

interface OrderSummaryRepository:JpaRepository<OrderSummary, String>
interface ProductSummaryRepository:JpaRepository<ProductSummary, String>
interface ProductRepository:Repository<Product>

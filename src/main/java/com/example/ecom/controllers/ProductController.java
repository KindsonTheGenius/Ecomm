package com.example.ecom.controllers;

import com.example.ecom.api.AddProductCommand;
import com.example.ecom.api.UpdateStockCommand;
import com.example.ecom.query.GetProductsQuery;
import com.example.ecom.query.ProductSummary;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class ProductController {

    private CommandGateway commandGateway;
    private QueryGateway queryGateway;

    public ProductController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/addProduct")
    public void handle(@RequestBody ProductSummary summary){
        AddProductCommand cmd = new AddProductCommand(
                summary.getId(),
                summary.getPrice(),
                summary.getStock(),
                summary.getDescription()
        );
        commandGateway.sendAndWait(cmd);
    }

    @GetMapping("/products")
    public CompletableFuture<List<ProductSummary>> getProducts(){
        return queryGateway.query(new GetProductsQuery(), ResponseTypes.multipleInstancesOf(ProductSummary.class));
    }
}

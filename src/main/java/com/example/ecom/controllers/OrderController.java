package com.example.ecom.controllers;

import com.example.ecom.api.CreateOrderCommand;
import com.example.ecom.query.GetOrdersQuery;
import com.example.ecom.query.OrderSummary;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class OrderController {

    //CommandGateway used CommandBus underneath
    private CommandGateway commandGateway;
    private QueryGateway queryGateway;

    public OrderController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/createOrder")
    public void handle(@RequestBody OrderSummary summary){
        CreateOrderCommand cmd = new CreateOrderCommand(
                summary.getId(),
                summary.getPrice(),
                summary.getNumber(),
                summary.getProductid()
        );
        commandGateway.send(cmd);
    }


    @GetMapping("/orders")
    public CompletableFuture<List<OrderSummary>> getOrders(){
        return  queryGateway.query(new GetOrdersQuery(), ResponseTypes.multipleInstancesOf(OrderSummary.class));
    }
}

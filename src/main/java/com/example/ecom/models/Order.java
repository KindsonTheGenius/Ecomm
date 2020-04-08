package com.example.ecom.models;

import com.example.ecom.api.CreateOrderCommand;
import com.example.ecom.api.OrderCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.Repository;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Order {
    @AggregateIdentifier
    private String  orderId;
    private Double price;
    private Integer number;
    private String productId;

    public Order(){
    }

    @CommandHandler
    public Order(CreateOrderCommand cmd){
        apply(
                new OrderCreatedEvent(
                        cmd.getOrderId(), cmd.getPrice(), cmd.getNumber(), cmd.getProductId()
                )
        );
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent evt){
        orderId = evt.getOrderId();
        price = evt.getPrice();
        number = evt.getNumber();
        productId = evt.getProductId();
    }
}

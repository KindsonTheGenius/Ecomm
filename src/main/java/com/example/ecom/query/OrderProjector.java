package com.example.ecom.query;

import com.example.ecom.api.OrderCreatedEvent;
import com.example.ecom.api.UpdateStockCommand;
import com.example.ecom.models.Product;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderProjector {

    private EventGateway eventGateway;
    private final OrderSummaryRepository repository;
    private final ProductRepository productRepository;

    public OrderProjector(EventGateway eventGateway, OrderSummaryRepository repository, ProductRepository productRepository) {
        this.eventGateway = eventGateway;
        this.repository = repository;
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(OrderCreatedEvent evt){
        repository.save(
                 new OrderSummary(
                        evt.getOrderId(),
                        evt.getPrice(),
                        evt.getNumber(),
                        evt.getProductId())
        );

        Product product = (Product) productRepository.load(evt.getProductId());
        product.updateStock(new UpdateStockCommand(evt.getProductId(),evt.getNumber()));

        //Fire a Stock updated event
        // StockUpdatedEvent event = new StockUpdatedEvent(evt.getProductId(), evt.getNumber());
        //eventGateway.publish(event);
    }

    @QueryHandler
    public List<OrderSummary> handle(GetOrdersQuery qry){
        return repository.findAll();
    }
}
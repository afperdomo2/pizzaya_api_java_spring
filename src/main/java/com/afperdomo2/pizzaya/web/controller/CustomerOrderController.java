package com.afperdomo2.pizzaya.web.controller;

import com.afperdomo2.pizzaya.persistence.entity.CustomerOrderEntity;
import com.afperdomo2.pizzaya.persistence.projection.OrderSummary;
import com.afperdomo2.pizzaya.service.CustomerOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.query.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Tag(name = "Customer Orders", description = "Operations related to customer orders")
public class CustomerOrderController {
    private final CustomerOrderService customerOrderService;

    public CustomerOrderController(CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }

    @GetMapping
    @Operation(summary = "Obtener todas las ordenes de clientes", description = "Devuelve una lista de todas las órdenes de clientes")
    @ApiResponse(responseCode = "200", description = "Órdenes encontradas")
    public ResponseEntity<List<CustomerOrderEntity>> findAll() {
        return ResponseEntity.ok(this.customerOrderService.findAll());
    }

    @GetMapping("/today")
    @Operation(summary = "Obtener las ordenes de clientes del día", description = "Devuelve una lista de las órdenes de clientes realizadas hoy")
    @ApiResponse(responseCode = "200", description = "Órdenes encontradas")
    public ResponseEntity<List<CustomerOrderEntity>> findAllToday() {
        return ResponseEntity.ok(this.customerOrderService.findAllToday());
    }

    @GetMapping("/outside")
    @Operation(summary = "Obtener las ordenes de clientes para llevar o delivery", description = "Devuelve una lista de las órdenes de clientes que son para llevar o delivery")
    @ApiResponse(responseCode = "200", description = "Órdenes encontradas")
    public ResponseEntity<List<CustomerOrderEntity>> findAllOutsideOrders() {
        return ResponseEntity.ok(this.customerOrderService.findAllOutsideOrders());
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Obtener las ordenes de un cliente específico", description = "Devuelve una lista de las órdenes de un cliente específico")
    @ApiResponse(responseCode = "200", description = "Órdenes encontradas")
    public ResponseEntity<List<CustomerOrderEntity>> findCustomerOrders(
            @Parameter(description = "ID del cliente", required = true, example = "1") Long customerId
    ) {
        return ResponseEntity.ok(this.customerOrderService.findCustomerOrders(customerId));
    }

    @GetMapping("/{orderId}/summary")
    @Operation(summary = "Obtener el resumen de una orden específica", description = "Devuelve el resumen de una orden específica")
    @ApiResponse(responseCode = "200", description = "Resumen de la orden encontrado")
    @ApiResponse(responseCode = "404", description = "Orden no encontrada")
    public ResponseEntity<OrderSummary> findOrderSummary(
            @Parameter(description = "ID de la orden", required = true, example = "1") Long orderId
    ) {
        OrderSummary orderSummary = this.customerOrderService.findOrderSummary(orderId);
        if (orderSummary == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderSummary);
    }
}

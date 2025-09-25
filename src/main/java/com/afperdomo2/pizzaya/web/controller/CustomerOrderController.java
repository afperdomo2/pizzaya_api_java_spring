package com.afperdomo2.pizzaya.web.controller;

import com.afperdomo2.pizzaya.persistence.entity.CustomerOrderEntity;
import com.afperdomo2.pizzaya.service.CustomerOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
}

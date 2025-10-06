package com.afperdomo2.pizzaya.web.controller;

import com.afperdomo2.pizzaya.persistence.entity.CustomerEntity;
import com.afperdomo2.pizzaya.persistence.entity.CustomerOrderEntity;
import com.afperdomo2.pizzaya.service.CustomerOrderService;
import com.afperdomo2.pizzaya.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
@Tag(name = "Customers", description = "Operaciones relacionadas con los clientes")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerOrderService customerOrderService;

    public CustomerController(CustomerService customerService, CustomerOrderService customerOrderService) {
        this.customerService = customerService;
        this.customerOrderService = customerOrderService;
    }

    @GetMapping("/phone/{phone}")
    @Operation(summary = "Obtener cliente por número de teléfono", description = "Devuelve un cliente basado en su número de teléfono")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    public ResponseEntity<CustomerEntity> findByPhone(
            @Parameter(description = "Número de teléfono del cliente", example = "1234567890") @PathVariable String phone
    ) {
        CustomerEntity customer = this.customerService.findByPhone(phone);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/{customerId}/orders")
    @Operation(summary = "Obtener las ordenes de un cliente específico", description = "Devuelve una lista de las órdenes de un cliente específico")
    @ApiResponse(responseCode = "200", description = "Órdenes encontradas")
    public ResponseEntity<List<CustomerOrderEntity>> findCustomerOrders(
            @Parameter(description = "ID del cliente", example = "1") @PathVariable Long customerId
    ) {
        return ResponseEntity.ok(this.customerOrderService.findCustomerOrders(customerId));
    }
}

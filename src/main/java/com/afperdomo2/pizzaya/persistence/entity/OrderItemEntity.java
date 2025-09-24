package com.afperdomo2.pizzaya.persistence.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
public class OrderItemEntity {
    @EmbeddedId
    private OrderItemId id;

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    public static class OrderItemId {
        @Column(name = "customer_order_id")
        private Long customerOrderId;

        @Column(name = "pizza_id")
        private Long pizzaId;
    }

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal quantity;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    // Relations
    @ManyToOne
    @JoinColumn(name = "customer_order_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CustomerOrderEntity customerOrder;

    @ManyToOne
    @JoinColumn(name = "pizza_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PizzaEntity pizza;
}

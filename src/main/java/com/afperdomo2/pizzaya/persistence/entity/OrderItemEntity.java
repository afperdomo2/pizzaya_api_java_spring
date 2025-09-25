package com.afperdomo2.pizzaya.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

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
    @ManyToOne()
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "customer_order_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private CustomerOrderEntity customerOrder;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "pizza_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PizzaEntity pizza;
}

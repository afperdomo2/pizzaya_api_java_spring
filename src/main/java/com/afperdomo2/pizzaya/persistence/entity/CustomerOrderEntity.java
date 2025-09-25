package com.afperdomo2.pizzaya.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "customer_orders")
@Getter
@Setter
@NoArgsConstructor
public class CustomerOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Column()
    private String notes;

    @Column(name = "order_type", nullable = false, columnDefinition = "CHAR(1)")
    private String orderType;

    @Column(nullable = false)
    private LocalDateTime date;

    // Relations
    @ManyToOne(fetch = FetchType.LAZY) // Lazy para evitar carga innecesaria
    @JoinColumn(name = "customer_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private CustomerEntity customer;

    @OneToMany(mappedBy = "customerOrder", fetch = FetchType.EAGER) // Eager para cargar los items junto con la orden
    private List<OrderItemEntity> items;
}

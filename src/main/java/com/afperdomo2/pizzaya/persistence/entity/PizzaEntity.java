package com.afperdomo2.pizzaya.persistence.entity;

import com.afperdomo2.pizzaya.persistence.audit.AuditPizzaListener;
import com.afperdomo2.pizzaya.persistence.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@EntityListeners({AuditingEntityListener.class, AuditPizzaListener.class})
@Table(name = "pizzas")
@Getter
@Setter
@NoArgsConstructor
public class PizzaEntity extends AuditableEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "is_vegetarian", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isVegetarian;

    @Column(name = "is_vegan", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isVegan;

    @Column(name = "is_available", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isAvailable;

    @Override
    public String toString() {
        return "PizzaEntity{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + ", price=" + price + ", isVegetarian=" + isVegetarian + ", isVegan=" + isVegan + ", isAvailable=" + isAvailable + '}';
    }
}

package com.afperdomo2.pizzaya.persistence.entity;

import com.afperdomo2.pizzaya.persistence.audit.AuditPizzaListener;
import com.afperdomo2.pizzaya.persistence.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Entity
@EntityListeners({AuditingEntityListener.class, AuditPizzaListener.class})
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity extends AuditableEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "is_active", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isActive;

    @Column(name = "is_blocked", columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isBlocked;
}

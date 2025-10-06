package com.afperdomo2.pizzaya.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
@NoArgsConstructor
public class UserRoleEntity {
    @EmbeddedId
    private UserRoleId id;

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    public static class UserRoleId {
        @Column(name = "user_id")
        private Long userId;

        @Column(name = "role")
        private String role;
    }

    @Column(name = "granted_date", nullable = false)
    private LocalDateTime grantedDate;

    @ManyToOne()
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;
}

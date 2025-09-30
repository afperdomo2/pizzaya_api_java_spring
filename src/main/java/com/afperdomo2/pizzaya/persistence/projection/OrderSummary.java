package com.afperdomo2.pizzaya.persistence.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface OrderSummary {
    Long getId();

    LocalDateTime getDate();

    BigDecimal getTotal();

    Long getCustomerId();

    String getCustomerName();

    String getPizzaNames();
}

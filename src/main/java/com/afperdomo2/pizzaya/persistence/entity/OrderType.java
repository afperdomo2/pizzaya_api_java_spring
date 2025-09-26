package com.afperdomo2.pizzaya.persistence.entity;

public enum OrderType {
    DELIVERY("D"),
    CARRYOUT("C"),
    ONSITE("S");

    private final String code;

    OrderType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static OrderType fromCode(String code) {
        for (OrderType type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Tipo de orden desconocido: " + code);
    }
}


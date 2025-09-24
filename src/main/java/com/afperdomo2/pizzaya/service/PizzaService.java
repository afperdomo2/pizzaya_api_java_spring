package com.afperdomo2.pizzaya.service;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.afperdomo2.pizzaya.persistence.entity.PizzaEntity;

@Service
public class PizzaService {
    private final JdbcTemplate jdbcTemplate;

    public PizzaService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<PizzaEntity> getAllPizzas() {
        return this.jdbcTemplate.query("SELECT * FROM pizzas", new BeanPropertyRowMapper<>(PizzaEntity.class));
    }
}

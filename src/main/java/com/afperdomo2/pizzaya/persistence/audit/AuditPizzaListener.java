package com.afperdomo2.pizzaya.persistence.audit;

import com.afperdomo2.pizzaya.persistence.entity.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;

public class AuditPizzaListener {
    private PizzaEntity currentValue;

    @PostLoad
    public void postLOad(PizzaEntity entity) {
        System.out.println("--> POST LOAD");
        this.currentValue = SerializationUtils.clone(entity);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(PizzaEntity entity) {
        System.out.println("--> ON POST PERSIST/UPDATE");
        System.out.println("----> Previous Value: " + (currentValue != null ? currentValue.toString() : "null"));
        System.out.println("----> Current Value: " + entity.toString());
    }

    @PreRemove
    public void onPreDelete(PizzaEntity entity) {
        System.out.println("--> Entity about to be deleted: " + entity.toString());
    }
}

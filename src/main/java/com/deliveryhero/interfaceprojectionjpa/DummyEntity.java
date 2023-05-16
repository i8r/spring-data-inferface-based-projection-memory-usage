package com.deliveryhero.interfaceprojectionjpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DummyEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String string;

    protected DummyEntity() {}

    public DummyEntity(Long id, String string) {
        this.id = id;
        this.string = string;
    }

    public Long getId() {
        return id;
    }
    public String getString() {
        return string;
    }
}

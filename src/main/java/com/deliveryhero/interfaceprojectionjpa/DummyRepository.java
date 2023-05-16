package com.deliveryhero.interfaceprojectionjpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DummyRepository extends CrudRepository<DummyEntity, Long> {

    @Query(value = "SELECT de.id AS id FROM DummyEntity AS de")
    List<DummyIdProjection> getIdProjections();


    @Query(value = "SELECT de.id AS id FROM DummyEntity AS de")
    List<Long> getIds();
}

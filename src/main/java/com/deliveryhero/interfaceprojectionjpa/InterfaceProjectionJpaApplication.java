package com.deliveryhero.interfaceprojectionjpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

import static java.util.stream.Collectors.joining;

@SpringBootApplication
public class InterfaceProjectionJpaApplication implements ApplicationRunner {
    Logger logger = LoggerFactory.getLogger(InterfaceProjectionJpaApplication.class);

    @Autowired
    DummyRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(InterfaceProjectionJpaApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        step();
        logger.info("Saving entities");
        for (int i = 0; i < 100_000; i++) {
            repository.save(new DummyEntity(null, "entry  #" + i));
        }
        logger.info("Trigger GC");
        System.gc();
        step();
        {
            logger.info("Getting ids with projection");
            List<DummyIdProjection> ids = repository.getIdProjections();

            step();
            logger.info("Trigger GC");
            System.gc();
            step();
            logger.info("Got ids: " + ids.stream()
                    .map(DummyIdProjection::getId)
                    .map(Object::toString)
                    .collect(joining(", ")));
        }
        logger.info("Trigger GC");
        System.gc();
        step();
        {
            logger.info("Getting ids without projection");
            List<Long> ids = repository.getIds();

            step();
            logger.info("Trigger GC");
            System.gc();
            step();
            logger.info("Got ids: " + ids.stream()
                    .map(Object::toString)
                    .collect(joining(", ")));
        }
        logger.info("Trigger GC");
        System.gc();
        step();
    }

    void step() throws InterruptedException {
        Thread.sleep(10000);
    }
}

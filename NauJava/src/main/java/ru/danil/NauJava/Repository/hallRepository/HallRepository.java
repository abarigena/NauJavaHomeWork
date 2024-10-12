package ru.danil.NauJava.Repository.hallRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.danil.NauJava.Entities.Hall.Hall;

import java.util.List;

public interface HallRepository extends CrudRepository<Hall, Long> {
    List<Hall> findByName(String name);

    void deleteByName(String hallName);

}

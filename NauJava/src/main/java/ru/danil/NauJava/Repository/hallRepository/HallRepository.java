package ru.danil.NauJava.Repository.hallRepository;


import org.springframework.data.repository.CrudRepository;

import ru.danil.NauJava.Entities.Film.Film;
import ru.danil.NauJava.Entities.Hall.Hall;

import java.util.List;

/**
 * Репозиторий для управления сущностями {@link Hall}.
 * Предоставляет методы для выполнения CRUD-операций с объектами фильма в базе данных.
 */
public interface HallRepository extends CrudRepository<Hall, Long> {

    /**
     * Удаляет зал по его названию.
     *
     * @param hallName название зала, который необходимо удалить
     */
    void deleteByName(String hallName);

}

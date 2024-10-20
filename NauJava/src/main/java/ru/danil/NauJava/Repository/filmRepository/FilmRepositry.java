package ru.danil.NauJava.Repository.filmRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.danil.NauJava.Entities.Film.Film;

/**
 * Репозиторий для управления сущностями {@link Film}.
 * Предоставляет методы для выполнения CRUD-операций с объектами фильма в базе данных.
 */

@RepositoryRestResource
public interface FilmRepositry extends CrudRepository<Film, Long> {
}

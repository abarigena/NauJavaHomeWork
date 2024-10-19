package ru.danil.NauJava.Repository.filmRepository;

import org.springframework.data.repository.CrudRepository;
import ru.danil.NauJava.Entities.Film.Film;

/**
 * Репозиторий для управления сущностями {@link Film}.
 * Предоставляет методы для выполнения CRUD-операций с объектами фильма в базе данных.
 */
public interface FilmRepositry extends CrudRepository<Film, Long> {
}

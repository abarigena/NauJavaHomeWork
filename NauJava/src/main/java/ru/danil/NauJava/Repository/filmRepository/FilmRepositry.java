package ru.danil.NauJava.Repository.filmRepository;

import org.springframework.data.repository.CrudRepository;
import ru.danil.NauJava.Entities.Film.Film;

public interface FilmRepositry extends CrudRepository<Film, Long> {
}

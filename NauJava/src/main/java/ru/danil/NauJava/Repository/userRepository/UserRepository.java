package ru.danil.NauJava.Repository.userRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.danil.NauJava.Entities.User.User;

import java.util.List;


/**
 * Репозиторий для управления сущностями {@link User}.
 * Предоставляет методы для выполнения CRUD-операций с объектами фильма в базе данных.
 */
@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}

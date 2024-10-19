package ru.danil.NauJava.Repository.userRepository;

import org.springframework.data.repository.CrudRepository;
import ru.danil.NauJava.Entities.User.User;


/**
 * Репозиторий для управления сущностями {@link User}.
 * Предоставляет методы для выполнения CRUD-операций с объектами фильма в базе данных.
 */
public interface UserRepository extends CrudRepository<User, Long> {

}

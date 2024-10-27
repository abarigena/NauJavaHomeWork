package ru.danil.NauJava.Repository.userRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.danil.NauJava.Entities.User.User;


/**
 * Репозиторий для управления сущностями {@link User}.
 * Предоставляет методы для выполнения CRUD-операций с объектами фильма в базе данных.
 */
@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Возвращает объект {@link User} по его имени пользователя.
     *
     * @param username имя пользователя, для которого нужно найти запись
     * @return объект {@link User}, соответствующий указанному имени пользователя
     */
    User findByUsername(String username);
}

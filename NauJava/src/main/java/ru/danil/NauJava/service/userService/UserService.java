package ru.danil.NauJava.service.userService;

import ru.danil.NauJava.Entities.User.User;

/**
 * Сервис для управления объектами сущности {@link User}.
 */
public interface UserService {

    /**
     * Добавляет нового пользователя в систему.
     *
     * @param user объект {@link User}, который нужно добавить
     */
    void addUser(User user);

    /**
     * Находит пользователя по имени пользователя.
     *
     * @param username имя пользователя, по которому необходимо выполнить поиск
     * @return объект {@link User}, соответствующий указанному имени пользователя
     */
    User findByUsername(String username);
}

package ru.danil.NauJava.service.userService;
import ru.danil.NauJava.Entities.User.User;

public interface UserService {
    void addUser(User user);
    User findByUsername(String username);
}

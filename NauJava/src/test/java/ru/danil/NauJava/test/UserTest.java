package ru.danil.NauJava.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.danil.NauJava.Entities.User.User;
import ru.danil.NauJava.service.userService.UserService;

import java.util.UUID;

@SpringBootTest
class UserTest {
    private final UserService userService;

    @Autowired
    public UserTest(final UserService userService) {
        this.userService = userService;
    }

    @Test
    void testFindByUsername() {
        String usernameTest = UUID.randomUUID().toString();
        String passwordTest = UUID.randomUUID().toString();

        User user = new User();
        user.setUsername(usernameTest);
        user.setPassword(passwordTest);


        userService.addUser(user);

        User foundUser = userService.findByUsername(usernameTest);

        Assertions.assertEquals(foundUser.getUsername(), user.getUsername());

        Assertions.assertNotEquals(foundUser, null);

    }
}

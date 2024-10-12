package ru.danil.NauJava.Repository.userRepository;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import ru.danil.NauJava.Entities.User.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

}

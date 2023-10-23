package io.github.luizns.todolist.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.github.luizns.todolist.domain.model.User;
import io.github.luizns.todolist.domain.repository.IUserRepository;
import io.github.luizns.todolist.service.UserService;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    private final IUserRepository repository;

    public UserServiceImpl(IUserRepository repository) {
        this.repository = repository;
    }


    @Override
    public User create(User userToCreate) {
        if (repository.existsByUsername(userToCreate.getUsername())) {
            throw new IllegalArgumentException("This Username already exists.");
        }

        var passwordHashred = BCrypt.withDefaults()
                .hashToString(12, userToCreate.getPassword().toCharArray());
        userToCreate.setPassword(passwordHashred);
        return repository.save(userToCreate);

    }

    @Override
    public User findById(UUID id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);

    }
}

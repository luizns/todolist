package io.github.luizns.todolist.service;

import io.github.luizns.todolist.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface UserService {

     User create(User userToCreate) ;

     User findById(UUID id);
}

package br.com.codebydias.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepostitory extends JpaRepository<UserModel, UUID> {

    UserModel findByUsername(String username);

    
}

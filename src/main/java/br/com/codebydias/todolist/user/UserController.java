package br.com.codebydias.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    private IUserRepostitory userRepostitory;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody UserModel userModel) {

        var user = this.userRepostitory.findByUsername(userModel.getUsername());
        if (user != null) {

            System.out.println("Usuário já existe");
            return ResponseEntity.status(400).body("Usuario já existe");
        }

        var hashedPassword = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(hashedPassword);

        var userCreated = this.userRepostitory.save(userModel);
        return ResponseEntity.status(201).body("Usuario " + userCreated.getUsername() + " criado com sucesso");
    }

}

package br.com.codebydias.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
// import jakarta.persistence.Column;
// import lombok.Getter;
// import lombok.Setter;

@Data //    ambos
     //     @Getter  // apenas getters
    //      @Setter // apenas setters

 // Chamar em cima da class para ser geral
//  Chamar no atributo pra ser individual
@Entity(name = "tb_users")
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    // @Column(name = "usuario") Para substituir e definir separadamente o nome da coluna
    //define a coluna com index unique true
    @Column(unique = true)
    private String username;
    private String name;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    /*
     * getter and setter
     * servem para recuperar dados especificos do parametro (private)
     * recuperando no controller EX:
     * |-> System.out.println(userModel.getUsername());
     * 
     * getter = você digita a senha e consegue ver o que tem dentro.
     * setter = você digita a senha e consegue colocar algo lá dentro.
     * Getter Retorna o valor get
     * Setter Altera o valor set
     */

    // Chamar manualmente cada atributo

    // public void setUsername(String username) {
    // this.username = username;
    // }

    // public String getUsername() {
    // return username;
    // }

    // public void setName(String name) {
    // this.name = name;
    // }

    // public String getName() {
    // return name;
    // }

    // public void setPassword(String password) {
    // this.password = password;
    // }

    // public String getPassword() {
    // return password;
    // }

}

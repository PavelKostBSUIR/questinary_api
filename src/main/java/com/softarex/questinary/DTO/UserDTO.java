package com.softarex.questinary.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softarex.questinary.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String login;
    private String name;
    private String surname;
    private String phoneNumber;
    private String password;
    @JsonIgnore
    private String repeatedPassword;

    public UserDTO(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.phoneNumber = user.getPhoneNumber();
    }
}

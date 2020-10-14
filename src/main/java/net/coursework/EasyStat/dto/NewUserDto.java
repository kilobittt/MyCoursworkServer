package net.coursework.EasyStat.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import net.coursework.EasyStat.model.User;

import java.util.Date;

@JsonIgnoreProperties
@Data
public class NewUserDto {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User toUser(){
        User user = new User();
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setCreated(new Date());
        user.setUpdated(new Date());
        return user;
    }

    public static NewUserDto fromUser(User user) {
        NewUserDto newUserDto = new NewUserDto();
        newUserDto.setPassword(user.getPassword());
        newUserDto.setUsername(user.getUsername());
        newUserDto.setFirstName(user.getFirstName());
        newUserDto.setLastName(user.getLastName());
        newUserDto.setEmail(user.getEmail());

        return newUserDto;
    }

}

package ru.buzynnikov.bidservice.dto;

import lombok.Data;

//Для получения данных от клиента
@Data
public class UserDTO {
    private String login;
    private String password;
    private String confirmPassword;
}

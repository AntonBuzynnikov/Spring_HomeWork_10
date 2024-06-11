package ru.buzynnikov.bidservice.security.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.buzynnikov.bidservice.dto.UserDTO;
import ru.buzynnikov.bidservice.security.models.User;
import ru.buzynnikov.bidservice.security.services.UserService;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService service;

    //Данный метод отвечает за ругистрацию пользователей
    @PostMapping("/registry")
    public ResponseEntity<User> registry(@RequestBody UserDTO registryData){
        if(!registryData.getConfirmPassword().equals(registryData.getPassword())) throw new RuntimeException("Пароли не свовпадают");
        return new ResponseEntity<>(service.registry(registryData), HttpStatus.OK);
    }
}

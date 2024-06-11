package ru.buzynnikov.bidservice.security.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.buzynnikov.bidservice.dto.UserDTO;
import ru.buzynnikov.bidservice.security.models.User;
import ru.buzynnikov.bidservice.security.repositories.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final PasswordEncoder encoder;
    private final UserRepository repository;

    // поиск пользователя в базе данных
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = repository.findByLogin(username);
        return userOptional.orElseThrow();
    }

    public User registry(UserDTO dto){
        return repository.save(createUser(dto));
    }

    //поиск пользователя в базе данных и сохранение в БД, если пользователь не найден
    private User createUser(UserDTO dto){
        Optional<User> userOptional = repository.findByLogin(dto.getLogin());
        if (userOptional.isPresent()){
            throw new RuntimeException("Пользователь с " + dto.getLogin() + " уже зарегистрирован");
        }
        User user = new User();
        user.setLogin(dto.getLogin());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRole("ROLE_USER");
        return user;
    }
}

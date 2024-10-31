package org.example.global.auth.user.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.User.UserEntity;
import org.example.global.auth.user.dto.User;
import org.example.global.auth.user.exception.CannotFoundUserException;
import org.example.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserManagementService {

    private final UserRepository userRepository;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Transactional
    public void registerUser(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userRepository.save(userEntity);
    }

    @Transactional
    public void deleteUser(String username) {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(CannotFoundUserException::new);
        userRepository.delete(userEntity);
    }

    public void updateUsername(User user){
        UserEntity userEntity = new UserEntity();
        if(user.getUsername() != null){
            userEntity.setUsername(user.getUsername());
        }
        userRepository.save(userEntity);
    }

    public UserEntity selectUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }

    public boolean userConfirm(String username,String password){
        UserEntity findUser = selectUserByUsername(username);
        return encoder.matches(password, findUser.getPassword());
    }


}
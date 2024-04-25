package com.example.MyVet.User.users;

import com.example.MyVet.Exceptions.EntityNotFoundException;
import com.example.MyVet.Exceptions.UserNotFoundException;
import jakarta.persistence.*;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return getUserByEmail(username);
    }

    public User getUserByEmail(String email) {
        return userRepository
                        .findByEmail(email)
                        .orElseThrow(() -> new UserNotFoundException(email));
    }

    public User getUserByVerificationCode(String verificationCode){
        return userRepository
                        .findByVerificationCode(verificationCode)
                        .orElseThrow(() -> new RuntimeException("Verification code not found"));
    }

    public void createUser(User user) throws EntityExistsException {
        if (Boolean.TRUE.equals(existsUserById(user.getId())))
            throw new EntityExistsException("User already exists: " + user.getId());
        if (Boolean.TRUE.equals(existsUserByEmail(user.getEmail())))
            throw new EntityExistsException("User already exists: " + user.getEmail());
        userRepository.save(user);
    }

    public void updateUser(User user) {
        if (Boolean.FALSE.equals(existsUserById(user.getId())))
            throw new EntityExistsException("User already exists: " + user.getId());
        userRepository.save(user);
    }

    public Boolean existsUserByEmail(String email) {
        if (email != null)
            return userRepository.existsByEmail(email);
        return Boolean.FALSE;
    }

    public Boolean existsUserById(String id) {
        if (id != null)
            return userRepository.existsById(id);
        return Boolean.FALSE;
    }


    public List<User> getAll() { return userRepository.findAll();}

    public Optional<User> getById(String id) {
        return userRepository.findById(id);
    }

    public User updateById(String id, UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            BeanUtils.copyProperties(userDTO, updatedUser);
            return userRepository.save(updatedUser);
        } else {
            throw new EntityNotFoundException(id);
        }
    }

    public User deleteById(String id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setEnabled(false);

            return userRepository.save(updatedUser);
        } else {
            throw new EntityNotFoundException(id);
        }
    }
}


package com.esprit.pifirstversion.service;


import com.esprit.pifirstversion.dtos.CreateUserRequest;
import com.esprit.pifirstversion.dtos.UpdateUserRequest;
import com.esprit.pifirstversion.dtos.UserResponse;
import com.esprit.pifirstversion.exeception.UserNotFoundException;
import com.esprit.pifirstversion.models.Role;
import com.esprit.pifirstversion.models.UserAPP;
import com.esprit.pifirstversion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        UserAPP user = UserAPP.builder()
                .firstName(request.firstname())
                .lastName(request.lastname())
                .username(request.username())
                .email(request.email())
                .phone(request.phone())
                .password(request.password())  // Ajouter un encryptage ici si nécessaire
                .role(Role.valueOf(request.role().toUpperCase())) // Convertir en Enum
                .build();

        UserAPP savedUser = userRepository.save(user);
        log.info("User {} is saved correctly", user.getUsername());
        return UserResponse.fromEntity(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(int id) {
        UserAPP user = findUserById(id);
        return UserResponse.fromEntity(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse updateUser(int id, UpdateUserRequest request) {
        UserAPP user = findUserById(id);

        // Utilisation du Builder pour mise à jour partielle
        user = user.toBuilder()
                .firstName(request.firstname() != null ? request.firstname() : user.getFirstName())
                .lastName(request.lastname() != null ? request.lastname() : user.getLastName())
                .username(request.username() != null ? request.username() : user.getUsername())
                .email(request.email() != null ? request.email() : user.getEmail())
                .phone(request.phone() != null ? request.phone() : user.getPhone())
                .password(request.password() != null ? request.password() : user.getPassword())
                .role(request.role() != null ? Role.valueOf(request.role().toUpperCase()) : user.getRole())
                .build();

        UserAPP updatedUser = userRepository.save(user);
        return UserResponse.fromEntity(updatedUser);
    }

    @Override
    public void deleteUser(int id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public UserAPP findUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}

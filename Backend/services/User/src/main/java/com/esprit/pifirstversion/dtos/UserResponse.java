package com.esprit.pifirstversion.dtos;


import com.esprit.pifirstversion.models.UserAPP;

public record UserResponse(
        int id,
        String firstname,
        String lastname,
        String username,
        String email,
        String phone,
        String role
) {
    public static UserResponse fromEntity(UserAPP user) {
        return new UserResponse(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getRole().name()
        );
    }
}
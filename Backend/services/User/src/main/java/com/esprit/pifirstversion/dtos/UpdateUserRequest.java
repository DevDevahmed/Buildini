package com.esprit.pifirstversion.dtos;



public record UpdateUserRequest(
        String firstname,
        String lastname,
        String username,
        String email,
        String phone,
        String password,
        String role // Keep role as String and convert to Enum in Service
) {}

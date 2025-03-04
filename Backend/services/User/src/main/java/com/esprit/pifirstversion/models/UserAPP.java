package com.esprit.pifirstversion.models;
import jakarta.persistence.*;
import lombok.*;

@Builder(toBuilder = true)
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAPP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UserId;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(nullable = false)
    private String phone;
}

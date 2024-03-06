package dev.cluuny.jwtauthdemo.model.http;

import dev.cluuny.jwtauthdemo.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String country;
}

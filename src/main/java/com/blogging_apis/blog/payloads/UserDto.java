package com.blogging_apis.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;

    @NotBlank
    @Size(min=4, message = "Username must be min of 4 characters.")
    private String name;

    @Email(message = "Email address is not valid.")
    private String email;

    @NotBlank
    @Size(min =3, max=10, message = "Password must be min of 3 chars and max 10 chars")
    private String password;

    @NotBlank
    private String about;
}

package com.blog.payloads;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDto {
    private int id;
    @NotNull
    @Email(message = "Given email address is not valid")
    private String email;
    @NotEmpty
    @Size(min=3,max=10,message = "Password must be min of 3 and max of 10 characters")
    private String password;
    @NotEmpty
    @Size(min=4,message = "Username must be minimum of 4 characters")
    private String name;
    @NotEmpty
    private String about;
}

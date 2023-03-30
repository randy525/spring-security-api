package com.endava.restraining.entity.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "Username should not be blank or empty")
    private String username;

    @NotBlank(message = "Username should not be blank or empty")
    @Length(min = 8, message = "Password ")
    private String password;

    private String role;

}

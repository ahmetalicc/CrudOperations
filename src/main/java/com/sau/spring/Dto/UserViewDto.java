package com.sau.spring.Dto;

import com.sau.spring.Entity.User;
import lombok.Getter;

import java.io.Serializable;


@Getter
public final class UserViewDto implements Serializable {

    private final String firstName;

    private final String lastName;

    public UserViewDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static UserViewDto of(User user){
        return new UserViewDto(user.getFirstName(), user.getLastName());
    }
}

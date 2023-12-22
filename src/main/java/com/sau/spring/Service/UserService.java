package com.sau.spring.Service;


import com.sau.spring.Dto.UserCreateDto;
import com.sau.spring.Dto.UserUpdateDto;
import com.sau.spring.Dto.UserViewDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserViewDto getUserById(Long id);

    UserViewDto createUser(UserCreateDto userCreateDto);

    List<UserViewDto> getAll();

    UserViewDto updateUser(Long id, UserUpdateDto userUpdateDto);

    void deleteUser(Long id);

    List<UserViewDto> slice(Pageable pageable);

}

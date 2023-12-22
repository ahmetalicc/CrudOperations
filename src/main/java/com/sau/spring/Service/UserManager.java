package com.sau.spring.Service;

import com.sau.spring.Dto.UserCreateDto;
import com.sau.spring.Dto.UserUpdateDto;
import com.sau.spring.Dto.UserViewDto;
import com.sau.spring.Entity.User;
import com.sau.spring.Exceptions.NotFoundException;
import com.sau.spring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManager implements UserService{

    private UserRepository userRepository;
    @Autowired
    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public UserViewDto getUserById(Long id) {
      User user =  userRepository.findById(id).orElseThrow
                (() -> new NotFoundException("Not Found"));
      return UserViewDto.of(user);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<UserViewDto> getAll() {
        return userRepository.findAll().stream().map(UserViewDto::of).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserViewDto updateUser(Long id, UserUpdateDto userUpdateDto) {
       User user =  userRepository.findById(id).orElseThrow(() -> new NotFoundException("User is not founded"));
       user.setFirstName(userUpdateDto.getFirstName());
       user.setLastName(userUpdateDto.getLastName());
       User updatedUser = userRepository.save(user);
        return UserViewDto.of(updatedUser);
    }

    @Override
    @Transactional
    public UserViewDto createUser(UserCreateDto userCreateDto) {
        User user = new User( userCreateDto.getUserName(),userCreateDto.getFirstName(), userCreateDto.getLastName());
        userRepository.save(user);
        return UserViewDto.of(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User is not founded"));
        userRepository.deleteById(user.getId());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<UserViewDto> slice(Pageable pageable) {
        return userRepository
                .findAll(pageable).stream().map
                        (UserViewDto::of).collect(Collectors.toList());
    }



}

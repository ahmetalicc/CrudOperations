package com.sau.spring.Api;

import com.sau.spring.Core.Utilities.GenericResponse;
import com.sau.spring.Dto.UserCreateDto;
import com.sau.spring.Dto.UserUpdateDto;
import com.sau.spring.Dto.UserViewDto;
import com.sau.spring.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserApi {

    private UserService userService;

    @Autowired
    public UserApi(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/v1/user/{id}")
    public ResponseEntity<UserViewDto> getUserById(@PathVariable Long id){
        UserViewDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/v1/user/getAll")
    public ResponseEntity<List<UserViewDto>> getAllUsers(){
        List<UserViewDto> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/v1/user/add")
    public ResponseEntity<?> createUser(@RequestBody @Validated UserCreateDto userCreateDto){
        userService.createUser(userCreateDto);
        return ResponseEntity.ok(new GenericResponse("User created"));
    }

    @PutMapping("/v1/user/update/{id}")
    public ResponseEntity<UserViewDto> updateUser(@PathVariable Long id, @RequestBody UserUpdateDto userUpdateDto){
        UserViewDto user = userService.updateUser(id, userUpdateDto);
        return ResponseEntity.ok(user);
    }
    @DeleteMapping("v1/user/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok(new GenericResponse("User deleted"));
    }

    @GetMapping("/v1/user/slice")
    public ResponseEntity<List<UserViewDto>> slice(Pageable pageable){
        List<UserViewDto> users = userService.slice(pageable);
        return ResponseEntity.ok(users);
    }
}

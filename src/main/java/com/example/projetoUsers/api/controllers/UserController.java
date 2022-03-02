package com.example.projetoUsers.api.controllers;

import com.example.projetoUsers.application.dto.SignUpDTO;
import com.example.projetoUsers.domain.models.User;
import com.example.projetoUsers.domain.servicesImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.apache.tomcat.jni.Mmap.delete;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/list-users")
    public ResponseEntity listUsers(){
        return ResponseEntity.ok(userServiceImpl.listUsers());
    }

    @GetMapping("/get-user")
    public ResponseEntity<Object> getUser(@RequestParam("id") Long userId) {
        try {
            User user = userServiceImpl.getUser(userId);
            return ResponseEntity.ok(user);
        } catch(Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/sign-up")
    @Secured({ "ROLE_ADMIN" })
    public ResponseEntity<Object> signUp(@RequestBody SignUpDTO signUpDTO){
        try {
            User user = userServiceImpl.SignUp(signUpDTO);
            return ResponseEntity.ok(user);
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/update-user")
    @Secured({ "ROLE_ADMIN" })
    public ResponseEntity<Object> updateUser(@RequestParam("id") Long id,@RequestBody SignUpDTO signUpDTO) {
        try {
            User u = userServiceImpl.update(signUpDTO, id);
            return ResponseEntity.ok(signUpDTO);
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/delete-user")
    @Secured({ "ROLE_ADMIN" })
    public ResponseEntity<Object> deleteUser(@RequestParam("id") Long id){
        try {
            userServiceImpl.delete(id);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}

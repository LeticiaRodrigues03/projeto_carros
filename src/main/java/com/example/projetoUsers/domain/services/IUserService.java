package com.example.projetoUsers.domain.services;

import com.example.projetoUsers.application.dto.SignUpDTO;
import com.example.projetoUsers.domain.models.User;

public interface IUserService {
    User getUser(Long userId); //obter usuario
    User SignUp(SignUpDTO signUpDTO); //inscrever usuario
}

package com.example.projetoUsers.application.dto;


import com.example.projetoUsers.domain.models.Role;
import com.example.projetoUsers.domain.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

//Retornar dados resumidos

public class SignUpDTO {
    //Atributos para cadastrar usuários
    private Long id;
    private String login;
    private String nome;
    private String email;
    private String senha;

    // token jwt
    private String token;
    private List roles;

    public SignUpDTO(User user) {

    }

//    public SignUpDTO(User user) {
//    }

    public static SignUpDTO create(User user) {
        ModelMapper modelMapper = new ModelMapper();
        SignUpDTO dto = modelMapper.map(user, SignUpDTO.class);

        dto.roles = user.getRoles().stream()
                .map(Role::getNome)
                .collect(Collectors.toList());

        return dto;
    }

    public static SignUpDTO create(User user, String token) {
        SignUpDTO dto = create(user);
        dto.token = token;
        return dto;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper m = new ObjectMapper();
        return m.writeValueAsString(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SignUpDTO() {}


    public SignUpDTO(List roles, Long id,String login, String nome, String email, String senha, String token) {
        this.roles = roles;
        this.id = id;
        this.login = login;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.token = token;
    }
}

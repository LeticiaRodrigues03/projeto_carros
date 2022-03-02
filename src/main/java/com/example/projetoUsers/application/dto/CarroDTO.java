package com.example.projetoUsers.application.dto;


import com.example.projetoUsers.domain.models.Carro;
import org.modelmapper.ModelMapper;


public class CarroDTO {
    private Long id;
    private String nome;
    private String tipo;
    private String descricao;


    public static CarroDTO create(Carro carro) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(carro, CarroDTO.class);
    }

    public CarroDTO() {

    }

    public CarroDTO(Long id, String nome, String tipo, String descricao) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() { return descricao;}

    public void setDescricao(String descricao) { this.descricao = descricao;}
}


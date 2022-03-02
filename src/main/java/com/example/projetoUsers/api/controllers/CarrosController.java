package com.example.projetoUsers.api.controllers;

import com.example.projetoUsers.domain.models.Carro;
import com.example.projetoUsers.domain.models.User;
import com.example.projetoUsers.domain.servicesImpl.CarroServiceImpl;
import com.example.projetoUsers.application.dto.CarroDTO;
import com.example.projetoUsers.domain.servicesImpl.CarroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CarrosController {
    @Autowired
    private CarroServiceImpl service;

    @GetMapping("/list-carros")
    public ResponseEntity listCarros() {
        try{
            List<CarroDTO> carros = service.getCarros();
            return ResponseEntity.ok(carros);
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/get-carros")
    public ResponseEntity getCarrosById(@RequestParam("id") Long id) {
        try {
            CarroDTO c = service.getCarroById(id);
            return ResponseEntity.ok(c);
        } catch(Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/get-tipos")
    public ResponseEntity getCarrosByTipo(@RequestParam("tipo") String tipo) {
        try {
            List<CarroDTO> carros = service.getCarrosByTipo(tipo);
            return ResponseEntity.ok(carros);
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @PostMapping("/insert-carro")
    @Secured({ "ROLE_ADMIN" })
    public ResponseEntity insertCarro(@RequestBody Carro carro) {
        try {
            CarroDTO c = service.insert(carro);
            return ResponseEntity.ok(c);
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/update-carro")
    @Secured({ "ROLE_ADMIN" })
    public ResponseEntity updateCarro(@RequestParam("id") Long id, @RequestBody Carro carro) {
        carro.setId(id);
        try {
            CarroDTO c = service.update(carro, id);
            return ResponseEntity.ok(c);
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/delete-carro")
    @Secured({ "ROLE_ADMIN" })
    public ResponseEntity deleteCarro(@RequestParam("id") Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}

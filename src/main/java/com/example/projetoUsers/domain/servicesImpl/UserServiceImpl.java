package com.example.projetoUsers.domain.servicesImpl;

import com.example.projetoUsers.application.dto.CarroDTO;
import com.example.projetoUsers.application.dto.SignUpDTO;
import com.example.projetoUsers.data.repositories.UserRepository;
import com.example.projetoUsers.domain.models.User;
import com.example.projetoUsers.domain.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository rep;

    public List<SignUpDTO> listUsers(){
        return rep.findAll().stream().map(SignUpDTO::create).collect(Collectors.toList());
    }

    @Override
    public User getUser(Long userId) {
        Optional<User> fndUser = rep.findById(userId);

        if (!fndUser.isPresent()) throw new IllegalArgumentException("User not found");

        return fndUser.get();
    }

    @Override
    public User SignUp(SignUpDTO signUpDTO) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setNome(signUpDTO.getNome());
        user.setLogin(signUpDTO.getLogin());
        user.setEmail(signUpDTO.getEmail());
        user.setSenha(bCryptPasswordEncoder.encode(signUpDTO.getSenha()));

        User newUser = rep.save(user);

        return newUser;
    }

    public User delete(Long id) {
        Optional<User> user = getUserById(id);
        if (user.isPresent()) {
            rep.deleteById(id);
            return user.get();
        }
        return null;
    }

    private Optional<User> getUserById(Long id) {
        return rep.findById(id);
    }


    public User update(SignUpDTO signUpDTO, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        // Busca o carro no banco de dados
        Optional<User> optional = rep.findById(id);
        if(optional.isPresent()) {
            User db = optional.get();
            // Copiar as propriedades
            db.setNome(signUpDTO.getNome());
            db.setNome(signUpDTO.getNome());
            db.setLogin(signUpDTO.getLogin());
            db.setEmail(signUpDTO.getEmail());

            db.setSenha(bCryptPasswordEncoder.encode(signUpDTO.getSenha()));

            System.out.println("Usuário id " + db.getId());

            // Atualiza o carro
            rep.save(db);

            return db;
        } else {
            throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }
}

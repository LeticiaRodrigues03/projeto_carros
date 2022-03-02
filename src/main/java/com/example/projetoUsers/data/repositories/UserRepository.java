package com.example.projetoUsers.data.repositories;

import com.example.projetoUsers.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface UserRepository extends JpaRepository<User,Long>{
    User findByLogin(String login);
}

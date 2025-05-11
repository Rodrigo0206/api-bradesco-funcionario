package com.rodrigo.api_bradesco_funcionario.repositories;

import com.rodrigo.api_bradesco_funcionario.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
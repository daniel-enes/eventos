package com.demandas.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demandas.eventos.entity.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Integer> {

        Optional<User> findByEmail(String email);
}

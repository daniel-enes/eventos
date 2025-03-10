package com.demandas.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demandas.eventos.entity.User;

public interface UsersRepository extends JpaRepository<User, Integer> {

}

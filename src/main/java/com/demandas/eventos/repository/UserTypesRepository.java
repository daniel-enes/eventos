package com.demandas.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demandas.eventos.entity.UserType;

public interface UserTypesRepository extends JpaRepository<UserType, Integer>{

}

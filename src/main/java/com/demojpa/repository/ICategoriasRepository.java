package com.demojpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demojpa.models.Categoria;

public interface ICategoriasRepository extends JpaRepository<Categoria, Integer> {

}
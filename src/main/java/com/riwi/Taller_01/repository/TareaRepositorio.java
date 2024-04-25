package com.riwi.Taller_01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riwi.Taller_01.entity.Tarea;

public interface TareaRepositorio extends JpaRepository<Tarea,Long>{
    
}

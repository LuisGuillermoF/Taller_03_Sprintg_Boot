package com.riwi.Taller_01.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.riwi.Taller_01.entity.Tarea;
import com.riwi.Taller_01.repository.TareaRepositorio;

@Service
public class TareaService {


    @Autowired
    private TareaRepositorio objCodRepositorio;

    //Metodo para mostrar todas las tareas
    public List<Tarea> findAll(){
        return this.objCodRepositorio.findAll();
    }

    public Page<Tarea> finAllPaginate(int page,int size){
        if (page < 0) {
            page = 0;
        }
        Pageable objPageable = PageRequest.of(page, size);

        return this.objCodRepositorio.findAll(objPageable);
    }

    
    //Metodo para crear una nueva tarea
    public Tarea create (Tarea obTarea){
        return this.objCodRepositorio.save(obTarea);
    }


    //metodo para eliminar una tarea
    public void delete(Long id){
        this.objCodRepositorio.deleteById(id);;
    }


    //Metodo para buscar por id
    public Tarea findById(Long id){
        return this.objCodRepositorio.findById(id).orElse(null);
    }

    //Metodo para actualizar una tarea
    public Tarea update(Long id,Tarea objTarea){
        Tarea obTarea = this.findById(id);

        if (obTarea == null) return null;
            

        obTarea = objTarea;

        return this.objCodRepositorio.save(obTarea);
        }
}

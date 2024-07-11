package com.app.controllers;

import com.app.config.ApiError;
import com.app.entities.Priority;
import com.app.entities.User;
import com.app.repository.PriorityRepository;
import com.app.config.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/priority")
public class PriorityController {

    @Autowired
    PriorityRepository priorityRepository;


    @GetMapping("/all-priority")
    public List<Priority> getAllPriorities() {
        return priorityRepository.findAll();
    }


    @GetMapping("/{idBuscar}")//buscar prioridad por id
    public ResponseBody<Priority> getPriority(@PathVariable int id){
        Optional<Priority> priority = priorityRepository.findById(id);
        if (priority.isEmpty())
            return new ResponseBody<> ( new ApiError("No existe la prioridad con ese id: "+id, HttpStatus.NOT_FOUND),null);
        return new ResponseBody<>(null,priority.get());
    }

    @PostMapping("/add-priority")//metodo para crear el usuario
    public ResponseBody<Priority> createPriority(@RequestBody Priority priority) {

        if (priority == null)
            return new ResponseBody<>(new ApiError("los datos vienen nulos, no se pueden guardar",HttpStatus.NOT_FOUND),null);
        else{
            /*Optional<Priority> existingPriority = priorityRepository.findById(priority.getId()); este metodo lo tengo que cargar en el Repository
            if (existingPriority.isPresent())
            return new ResponseBody<>(new ApiError("La prioridad ya existe en la base",HttpStatus.NOT_FOUND),null);
            else {*/
            Priority priorityNew = priority;
            priorityNew.setId(priority.getId());
            priorityNew.setName(priority.getName());
            priorityNew.setCreated(LocalDate.now());
            priorityRepository.save(priorityNew);
            return new ResponseBody<>("Prioridad Guardada con exito",priority);//seria para mandar la prioridad y el mensaje.
        }
    }

    @PutMapping("/{idEdit}")//metodo para actualizar
    public ResponseBody<Priority> updatePriority(@PathVariable int id,@RequestBody Priority priority) {
        //comprueba que exista el usuario y si existe obtiene los valores
        if (priority == null)
            return new ResponseBody<>(new ApiError("los datos vienen nulos, no se pueden guardar",HttpStatus.NOT_FOUND),null);

        Optional<Priority> existingPriority = priorityRepository.findById(id);
        if (existingPriority.isPresent()) {
            existingPriority.get().setName((priority.getName()));
            // no se definio una fecha de modificado.
            priorityRepository.save(existingPriority.get());
            return new ResponseBody<>("Prioridad Modificada con exito",null);
        }
        else
            return new ResponseBody<>(new ApiError("La prioridad no existe en la base"+priority.getId(),HttpStatus.NOT_FOUND),null);
    }

    @PostMapping("/{idDelete}")//metodo para eliminar
    public ResponseBody<Priority> deletePriority(@PathVariable int id) {
        Optional<Priority> existingPriority = priorityRepository.findById(id);
        if (existingPriority.isEmpty())
            return new ResponseBody<>(new ApiError("La prioridad no existe en la base"+existingPriority.get().getId(),HttpStatus.NOT_FOUND),null);
        else
            priorityRepository.delete(existingPriority.get());

        return new ResponseBody<>("prioridad eliminada con exito",null);
    }

}
package com.app.controllers;

import com.app.config.ApiError;
import com.app.config.ResponseBody;
import com.app.entities.Priority;
import com.app.entities.Task;
import com.app.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;


    @GetMapping("/all-task")
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }


    @GetMapping("/{idBuscar}")//buscar prioridad por id
    public ResponseBody<Task> getTask(@PathVariable int id){
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty())
            return new ResponseBody<> ( new ApiError("No existe el task con ese id: "+id, HttpStatus.NOT_FOUND),null);
        return new ResponseBody<>(null,task.get());
    }

    @PostMapping("/add-task")//metodo para crear el usuario
    public ResponseBody<Task> createTask(@RequestBody Task task) {
        if (task == null)
            return new ResponseBody<>(new ApiError("los datos vienen nulos, no se pueden guardar",HttpStatus.NOT_FOUND),null);
        else{
        /*Optional<Task> existingTask = taskRepository.findById(task.getId());este metodo lo tengo que cargar en el Repository
        if (existingTask.isPresent())
            return new ResponseBody<>(new ApiError("El task ya existe en la base",HttpStatus.NOT_FOUND),null);
        else {*/
            Task taskNew = task;
            taskNew.setTitle(task.getTitle());
            taskNew.setDescription(task.getDescription());
            taskNew.setStatus(task.getStatus());
            taskNew.setCreated(LocalDate.now());
            taskRepository.save(taskNew);
            return new ResponseBody<>("Task Guardada con exito",task);//seria para mandar el task y el mensaje.
        }

    }

    @PutMapping("/{idEdit}")//metodo para actualizar
    public ResponseBody<Task> updateTask(@PathVariable int id,@RequestBody Task task) {
        //comprueba que exista el usuario y si existe obtiene los valores
        if (task == null)
            return new ResponseBody<>(new ApiError("los datos vienen nulos, no se pueden guardar",HttpStatus.NOT_FOUND),null);

        Optional<Task> existingTask = taskRepository.findById(id);
        if (existingTask.isPresent()) {
            existingTask.get().setTitle(task.getTitle());
            existingTask.get().setDescription(task.getDescription());
            existingTask.get().setStatus(task.getStatus());
            // no se definio una fecha de modificado.
            taskRepository.save(existingTask.get());
            return new ResponseBody<>("Task Modificado con exito",null);
        }
        else
            return new ResponseBody<>(new ApiError("El task no existe en la base"+task.getId(),HttpStatus.NOT_FOUND),null);
    }

    @PostMapping("/{idDelete}")//metodo para eliminar
    public ResponseBody<Task> deleteTask(@PathVariable int id) {
        Optional<Task> existingTask = taskRepository.findById(id);
        if (existingTask.isEmpty())
            return new ResponseBody<>(new ApiError("El task no existe en la base"+existingTask.get().getId(),HttpStatus.NOT_FOUND),null);
        else
            taskRepository.delete(existingTask.get());

        return new ResponseBody<>("Task eliminada con exito",null);
    }

}
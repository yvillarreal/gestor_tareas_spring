package com.app.controllers;

import com.app.config.ApiError;
import com.app.entities.User;
import com.app.repository.UserRepository;
import com.app.config.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;


    @GetMapping("/all-users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @GetMapping("/{idBuscar}")//buscar usuario por id
    public ResponseBody<User> getUser(@PathVariable int idBuscar){
        Optional<User> user = userRepository.findById(idBuscar);
        if (user.isEmpty())
            return new ResponseBody<> ( new ApiError("No existe usuario con ese id: "+idBuscar, HttpStatus.NOT_FOUND),null);
        return new ResponseBody<>(null,user.get());
    }

    @PostMapping("/add-user")//metodo para crear el usuario
    public ResponseBody<User> createUser(@RequestBody User user) {

        if (user == null)
            return new ResponseBody<>(new ApiError("los datos vienen nulos, no se pueden guardar",HttpStatus.NOT_FOUND),null);
        else{
           /* Optional<User> existingUser = userRepository.findById(user.getId());//este metodo no funciona aun.
            if (existingUser.isPresent())
                return new ResponseBody<>(new ApiError("El usuario ya existe en la base",HttpStatus.NOT_FOUND),null);
            else{*/
            User userNew = user;
            userNew.setUsername(user.getUsername());
            userNew.setFirstName(user.getFirstName());
            userNew.setLastName(user.getLastName());
            userNew.setPassword(user.getPassword());
            userNew.setCreated(LocalDate.now());
            userRepository.save(userNew);
            return new ResponseBody<>("Usuario Guardado con exito",user);//seria para mandar al usuario y el mensaje.
        }
    }

    @PutMapping("/{idEdit}")//metodo para actualizar
    public ResponseBody<User> updateUser(@PathVariable int idEdit,@RequestBody User user) {
        //comprueba que exista el usuario y si existe obtiene los valores
        if (user == null)
            return new ResponseBody<>(new ApiError("los datos vienen nulos, no se pueden guardar",HttpStatus.NOT_FOUND),null);

        Optional<User> existingUser = userRepository.findById(idEdit);
        if (existingUser.isPresent()) {
            existingUser.get().setUsername(user.getUsername());
            existingUser.get().setFirstName(user.getFirstName());
            existingUser.get().setLastName(user.getLastName());
            existingUser.get().setPassword(user.getPassword());
            // no se definio una fecha de modificado.
            userRepository.save(existingUser.get());
            return new ResponseBody<>("Usuario Modificado con exito",null);
        }
        else
            return new ResponseBody<>(new ApiError("El usuario no existe en la base"+user.getId(),HttpStatus.NOT_FOUND),null);
    }

    @PostMapping("/{idDelete}")//metodo para eliminar
    public ResponseBody<User> deleteUser(@PathVariable int idDelete) {
        Optional<User> existingUser = userRepository.findById(idDelete);
        if (existingUser.isEmpty())
            return new ResponseBody<>(new ApiError("El usuario no existe en la base"+existingUser.get().getId(),HttpStatus.NOT_FOUND),null);
        else
            userRepository.delete(existingUser.get());

        return new ResponseBody<>("Usuario eliminado con exito",null);
    }

}
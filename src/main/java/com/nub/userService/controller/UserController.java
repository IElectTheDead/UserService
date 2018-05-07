package com.nub.userService.controller;

import com.nub.userService.entity.User;
import com.nub.userService.model.ResponseModel;
import com.nub.userService.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    // 2: ok; 1: en proceso, 0: error
    @GetMapping("")
    public ResponseEntity<ResponseModel> listusers(){
        try{
            return ResponseEntity.ok().body(new ResponseModel<>(2, userService.findAll(), "Se encontró lo solicitado."));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseModel<>(0, null, "Ha ocurrido un error" + ex.getMessage()));
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel> find(@PathVariable("id") String id){
        try{
            User user = userService.find(id);
            return ResponseEntity.ok().body(new ResponseModel<>(2, user, "Se encontró lo solicitado."));
        }
        catch (NoSuchElementException noSuchElementException){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseModel<>(0, null, "No se realizó la operación, no existe un elemento con el ID solicitado."));
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseModel<>(0, null, "Ha ocurrido un error, " + ex.getMessage()));
        }

    }
    @PostMapping("")
    public ResponseEntity<ResponseModel> create(@RequestParam("id") String id, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("nickname") String nickname){
        try {
            User user = userService.createOrUpdate(new User(id, email, password, nickname));
            return ResponseEntity.ok().body(new ResponseModel<>(2, user, "Se creó lo solicitado."));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseModel<>(0, null, "Ha ocurrido un error, " + ex.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> update(@PathVariable("id") String id, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("nickname") String nickname) {
        try {
            User user = userService.find(id);
            user.setEmail(email);
            user.setPassword(password);
            user.setNickname(nickname);
            userService.createOrUpdate(user);
            return ResponseEntity.ok().body(new ResponseModel<>(2, user, "Se actualizó lo solicitado."));
        } catch (NoSuchElementException noSuchElementException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseModel<>(0, null, "No se realizó la operación, no existe un elemento con el ID solicitado."));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseModel<>(0, null, "Ha ocurrido un error, " + ex.getMessage()));
        }
    }
}

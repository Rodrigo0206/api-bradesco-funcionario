package com.rodrigo.api_bradesco_funcionario.controllers;


import java.util.List;

import com.rodrigo.api_bradesco_funcionario.entities.User;
import com.rodrigo.api_bradesco_funcionario.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public List<User> findAll() {
        List<User> result = repository.findAll();
        return result;
    }

    @GetMapping(value = "/{id}")
    public User findById(@PathVariable Long id) {
        User result = repository.findById(id).get();
        return result;
    }

    @PostMapping
    public User insert(@RequestBody User user) {
        User result = repository.save(user);
        return result;
    }

    @PutMapping(value = "/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        User entity = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setDepartment(user.getDepartment());

        return repository.save(entity);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }


}
package com.user.userManagement.web.controller;

import com.user.userManagement.entities.User;
import com.user.userManagement.service.SecurityService;
import com.user.userManagement.service.UserService;
import com.user.userManagement.validator.UserValidator;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dinanjanag on 3/20/19.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/")
    public @ResponseBody
    ResponseEntity<String> registration(@RequestBody User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(String.format("Can't create user. %s", bindingResult.toString()), HttpStatus.NOT_ACCEPTABLE);
        }
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/")
    public @ResponseBody ResponseEntity<String> updateUser(@RequestBody User user) {
        userService.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

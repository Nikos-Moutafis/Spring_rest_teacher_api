package gr.aueb.cf.schoolapp.controller;

import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.AutoLoginService;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RegisterController {
    private final IUserService userService;
    private final UserValidator userValidator;

    private final AutoLoginService autoLoginService;

    @Autowired
    public RegisterController(IUserService userService, UserValidator userValidator, AutoLoginService autoLoginService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.autoLoginService = autoLoginService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO, BindingResult bindingResult) {
        userValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }

        User user = userService.registerUser(userDTO);
        String response = ("User: " + user.getUsername() + " registered successfully and he is logged in");
        autoLoginService.autoLogin(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}

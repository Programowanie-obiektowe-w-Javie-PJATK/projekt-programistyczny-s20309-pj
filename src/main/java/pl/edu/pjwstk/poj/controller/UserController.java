package pl.edu.pjwstk.poj.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.poj.dto.UserDto;
import pl.edu.pjwstk.poj.entity.User;
import pl.edu.pjwstk.poj.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserDto userRegistrationDto() {
        return new UserDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto,
                                      BindingResult result) {

        User exists = userService.findByEmail(userDto.getEmail());
        if (exists != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            return "registration";
        }

        userService.save(userDto);
      //  return "redirect:/registration?success";
        return "redirect:/login";
    }

}

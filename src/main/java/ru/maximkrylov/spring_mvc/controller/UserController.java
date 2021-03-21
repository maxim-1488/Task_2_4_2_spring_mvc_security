package ru.maximkrylov.spring_mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.maximkrylov.spring_mvc.model.User;
import ru.maximkrylov.spring_mvc.service.RoleService;
import ru.maximkrylov.spring_mvc.service.UserService;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/user")
    public String userInfo(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "userpage";
    }

    @GetMapping(value = "/admin")
    public String listUsers(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "adminpage";
    }

    @GetMapping(value = "/users/new")
    public String newUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.addObject("roles", roleService.getAllRoles());
        return "new";
    }

    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "adminpage";
    }

    //страница для редактирования юзеров
    @GetMapping(value = "/edit/{id}")
    public String editUserForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "edit";
    }

    @PostMapping(value = "/edit")
    public String editUser(@ModelAttribute User user) {
            userService.updateUser(user);
        return "adminpage";
    }

    @GetMapping(value = "/remove/{id}")
    public String removeUser(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "adminpage";
    }
}

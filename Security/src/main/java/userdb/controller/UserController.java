package userdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import userdb.model.Role;
import userdb.model.User;
import userdb.service.UserService;

import java.util.Collections;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String allUsers(Model model) {
        model.addAttribute("usersList", userService.allUsers());
        return "admin";
    }


    @GetMapping("/admin/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getById(id));
        return "edit";
    }


    @PatchMapping("/admin/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String update(@PathVariable("id") int id,@ModelAttribute ("user") User user) {
        userService.edit(user);
        return "redirect:/admin";
    }


    @GetMapping("/admin/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }


    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String create(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/admin";
    }


    @DeleteMapping("/admin/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@PathVariable("id") int id) {
        userService.delete(userService.getById(id));
        return "redirect:/admin";
    }

    @GetMapping("admin/user/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "user";
    }
    @GetMapping("user/{username}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String showUser(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", userService.getUserByName(username));
        return "show";
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

}

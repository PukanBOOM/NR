package userdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import userdb.model.User;
import userdb.service.UserService;

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
    public String allUsers(Model model) {
        model.addAttribute("usersList", userService.allUsers());
        return "admin";
    }


    @GetMapping("/admin/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getById(id));
        return "edit";
    }


    @PatchMapping("/admin/{id}")
    public String update(@PathVariable("id") int id,@ModelAttribute ("user") User user) {
        userService.edit(user);
        return "redirect:/admin";
    }


    @GetMapping("/admin/add")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }


    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/admin";
    }


    @DeleteMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(userService.getById(id));
        return "redirect:/admin";
    }

    @GetMapping("user/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "user";
    }
}

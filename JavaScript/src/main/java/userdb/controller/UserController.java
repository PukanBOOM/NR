package userdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import userdb.model.User;
import userdb.service.UserService;

import java.util.List;

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

    @GetMapping(value = "/userMain")
    public String getUserPage() {
        return "show";
    }

    @GetMapping(value = "/adminMain")
    public String getAdminPage() {
        return "admin";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    public ResponseEntity<List<User>> allUsers(){
        final List<User> users =userService.allUsers();
        return users != null &&  !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/admin/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody User user){
        userService.edit(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @PostMapping()
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> create(@RequestBody User user){
        userService.add(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/admin/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> getOneUser(@PathVariable("id") int id) {
        User user = userService.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }



    @DeleteMapping("/admin/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        userService.delete(userService.getById(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("user")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<User> showUser(){
        User user = userService.getAuthUser();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

}

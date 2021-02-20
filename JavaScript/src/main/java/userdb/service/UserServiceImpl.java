package userdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import userdb.dao.UserDAO;
import userdb.model.Role;
import userdb.model.User;

import java.util.Collections;
import java.util.List;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Override

    public List<User> allUsers() {
        return userDAO.allUsers();
    }

    @Override

    public void add(User user) {
        user.setRoles(Collections.singleton(new Role(2,"ROLE_USER")));
        userDAO.add(user);
    }

    @Override

    public void delete(User user) {
        userDAO.delete(user);
    }

    @Override

    public void edit(User user) {
        userDAO.edit(user);
    }

    @Override

    public User getById(int id) {
        return userDAO.getById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.getUserByName(username);
    }

    @Override
    public User getUserByName(String username) {
        return userDAO.getUserByName(username);
    }

    @Override
    public User getAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email;
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            email = ((UserDetails)principal).getUsername();
        } else {
            email = principal.toString();
        }
            return (User) userDAO.getUserByName(email);
    }
}

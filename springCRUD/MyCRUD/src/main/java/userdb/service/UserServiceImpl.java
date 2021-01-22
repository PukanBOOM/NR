package userdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import userdb.dao.UserDAO;
import userdb.dao.UserDAOImpl;
import userdb.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
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
}

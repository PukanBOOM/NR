package hiber.dao;

import hiber.model.User;

import java.sql.ResultSet;
import java.util.List;

public interface UserDao {
   void add(User user);
   List<User> listUsers();

   void update(User user);

   User search(String model,int series);
}

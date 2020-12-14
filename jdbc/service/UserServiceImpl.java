package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl extends Util implements UserService {
    Connection connection = getConnection();
    Statement statement =connection.createStatement();

    public UserServiceImpl() throws SQLException, ClassNotFoundException {
    }

    public void createUsersTable() throws SQLException{

        statement.execute("CREATE TABLE IF NOT EXISTS USERS(id int not null PRIMARY KEY AUTO_INCREMENT,name varchar(45) not null,lastname varchar(45) not null,age int(3) not null)");

    }

    public void dropUsersTable() throws SQLException {
        statement.execute("DROP TABLE IF EXISTS USERS");
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String sql ="INSERT INTO USERS(name, lastname, age) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,lastName);
        preparedStatement.setInt(3,age);
        int rows =preparedStatement.executeUpdate();
        System.out.println("User с именем – "+ name +  " добавлен в базу данных");
    }

    public void removeUserById(long id) throws SQLException {
        String sql ="DELETE FROM USERS WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1,id);
        int rows =preparedStatement.executeUpdate();
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT id, name, lastname,age FROM USERS";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setLastName(resultSet.getString("lastname"));
            user.setAge(resultSet.getByte("age"));
            userList.add(user);
        }
        System.out.println(userList);
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        statement.execute("TRUNCATE TABLE USERS");
    }
}

package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //Util util = new Util();
        //Connection conn = util.getConnection();
        //Statement statement = util.statement(conn);
        UserDao user = new UserDaoJDBCImpl();
        user.createUsersTable();
        user.saveUser("Andrew","Levin", (byte) 22);
        user.saveUser("Petr","Petrov", (byte) 45);
        user.saveUser("Ivanov","Ivanov", (byte) 100);
        user.saveUser("Dmitriy","Dmitriev", (byte) 2);
        user.getAllUsers();
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}

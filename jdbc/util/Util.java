package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    public static void main(String[] args) {

    }
    public Connection getConnection() throws ClassNotFoundException{
    String url = "jdbc:mysql://localhost:3306/local?serverTimezone=Europe/Moscow";
        String userName = "root";
        String pass = "12345";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = null;
            try {conn = DriverManager.getConnection(url, userName, pass);
                //System.out.println("Connected");
            } finally {
                return conn;
            }

        }
        public Statement statement(Connection conn) throws SQLException{
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS USERS(id int not null,name varchar(45) not null,lastname varchar(45) not null,age int(3) not null)");
            return statement = null;
        }
    }


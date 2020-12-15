package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();
        userDao.saveUser("Andrew","Levin",(byte) 23);
        userDao.saveUser("Ivan","Ivanov",(byte) 100);
        userDao.saveUser("Petr","Petrov",(byte) 1);
        userDao.saveUser("Dmitriy","Dmitriev",(byte) 50);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();

    }
}

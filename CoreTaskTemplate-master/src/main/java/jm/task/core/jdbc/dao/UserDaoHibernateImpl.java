package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


    public UserDaoHibernateImpl() {

    }
    User user = new User();

    @Override
    public void createUsersTable() {

        Session session =  Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        String sql = "CREATE TABLE IF NOT EXISTS USER(id int not null PRIMARY KEY AUTO_INCREMENT,name varchar(45) not null,lastname varchar(45) not null,age int(3) not null)";
        Query query = session.createSQLQuery(sql);
        query.executeUpdate();
        tx1.commit();
        session.close();

    }

    @Override
    public void dropUsersTable() {
        Session session =  Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        String sql = "DROP TABLE IF EXISTS USER";
        Query query = session.createSQLQuery(sql);
        query.executeUpdate();
        tx1.commit();
        session.close();
        System.out.println("Done");

    }

    @Override
    public void saveUser(String name,String lastName, byte age) {
        user.setName(name);
        user.setAge(age);
        user.setLastName(lastName);
        Session session =  Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
        System.out.println("User с именем – "+ user.getName() +  " добавлен в базу данных");
    }

    @Override
    public void removeUserById(long id) {
        Session session =  Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        user.setId(id);
        session.delete(user);
        tx1.commit();
        session.close();


    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = (List<User>)  Util.getSessionFactory().openSession().createQuery("From User").list();
        System.out.println(userList);
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session =  Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        String hql = "delete from User";
        Query query = session.createQuery(hql);
        query.executeUpdate();
        tx1.commit();
        session.close();
    }
}

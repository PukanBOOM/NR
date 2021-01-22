package userdb.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import userdb.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public void setSessionFactory(EntityManager entityManager) {
        this.entityManager = entityManager;
    }



    @Override
    public List<User> allUsers() {
        return entityManager.createQuery("From User").getResultList();
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(User user) {
        entityManager.detach(user);
    }

    @Override
    public void edit(User user) {
        entityManager.refresh(user);
    }

    @Override
    public User getById(int id) {
        return entityManager.find(User.class, id);
    }
}

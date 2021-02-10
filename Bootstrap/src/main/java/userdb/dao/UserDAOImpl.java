package userdb.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import userdb.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
@Transactional(transactionManager = "transaction")
public class UserDAOImpl implements UserDAO  {
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
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(User user) {
        entityManager.getTransaction().begin();
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void edit(User user) {
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public User getById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByName(String username) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT user FROM User user WHERE user.username = :username", User.class);
        User user = query.setParameter("username", username)
                .getSingleResult();
        return user;
    }
}

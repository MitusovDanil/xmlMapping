package entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private SessionFactory sessionFactory;
    private Session session;

    @BeforeEach
    public void init() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

    }

    @Test
    public void insertIntoDataBase() {
        User user = new User();
        user.setName("Danil");
        user.setPassword("111");
        user.setEmail("111@111");
        session.save(user);
    }

    @Test
    public void getEntityFromDataBase() {
        User user = new User();
        user.setName("Danil");
        user.setPassword("111");
        user.setEmail("111@111");
        session.save(user);

        List result = session.createQuery("from User").list();
        assertTrue(result.contains(user));
    }

    @AfterEach
    public void close() {
        session.getTransaction().commit();
        sessionFactory.close();
    }

}
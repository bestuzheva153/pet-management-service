package example.dao;

import java.util.List;

import javax.swing.JOptionPane;

import example.model.Cat;
import jakarta.persistence.Query;
import example.interfaces.GenericDao;
import example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CatDao implements GenericDao<Cat> {

    @Override
    public Cat save(Cat cat) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.persist(cat);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Insertion error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return cat;
    }

    @Override
    public void deleteById(long id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Cat cat = session.get(Cat.class, id);
            if (cat != null) {
                session.remove(cat);
            }
            tx.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Delete by ID error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void deleteByEntity(Cat entity) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.remove(entity);
            tx.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Delete entity error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void deleteAll() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Cat");
            query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Delete all error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Cat update(Cat entity) {
        Session session = null;
        Cat updated = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            updated = (Cat) session.merge(entity);
            tx.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Update error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return updated;
    }

    @Override
    public Cat getById(long id) {
        Session session = null;
        Cat cat = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            cat = session.get(Cat.class, id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Get by ID error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return cat;
    }

    @Override
    public List<Cat> getAll() {
        Session session = null;
        List<Cat> cats = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            cats = session.createQuery("FROM Cat", Cat.class).list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Get all error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return cats;
    }
}
package example.dao;

import example.interfaces.GenericDao;
import example.model.Owner;
import example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.swing.JOptionPane;
import java.util.List;

public class OwnerDao implements GenericDao<Owner> {

    @Override
    public Owner save(Owner owner) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.persist(owner);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Insertion error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return owner;
    }

    @Override
    public void deleteById(long id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Owner owner = session.get(Owner.class, id);
            if (owner != null) {
                session.remove(owner);
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
    public void deleteByEntity(Owner entity) {
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
            Query<?> query = session.createQuery("DELETE FROM Owner");
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
    public Owner update(Owner entity) {
        Session session = null;
        Owner updated = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            updated = (Owner) session.merge(entity);
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
    public Owner getById(long id) {
        Session session = null;
        Owner owner = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            owner = session.get(Owner.class, id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Get by ID error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return owner;
    }

    @Override
    public List<Owner> getAll() {
        Session session = null;
        List<Owner> owners = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            owners = session.createQuery("FROM Owner", Owner.class).list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Get all error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return owners;
    }
}

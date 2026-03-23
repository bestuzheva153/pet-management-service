package example.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import example.model.Cat;
import example.model.Owner;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Создаем конфигурацию и явно указываем классы сущностей
            Configuration configuration = new Configuration()
                    .configure() // Загружает hibernate.cfg.xml
                    .addPackage("org.example.model") // Сканирует указанный пакет
                    .addAnnotatedClass(Owner.class)  // Явно регистрируем класс Owner
                    .addAnnotatedClass(Cat.class);   // Явно регистрируем класс Cat

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
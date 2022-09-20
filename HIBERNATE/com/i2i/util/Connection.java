package com.i2i.util;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.i2i.entity.Trainee;
import com.i2i.entity.Trainer;

/**             
 * <p>
 * Class which establish connection
 * with database
 * </p>
 *
 * @author Sanjai king
 *
 * @version 1
 *
 * @since 2022-07-18
 */
public class Connection{

    private static Connection connection;
    private static Session session = null;

    private Connection() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            configuration.addAnnotatedClass(Trainer.class);
            configuration.addAnnotatedClass(Trainee.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            session  = sessionFactory.openSession();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *used to return session for transaction
     *@return {@link Session}
     */  
    public static Session hibernateConnection()  {
        if (session == null || !session.isOpen())  {
            connection = new Connection();
        }
        return session;
    }
}
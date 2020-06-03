package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateInitialize {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    private static final Logger log = LoggerFactory.getLogger(HibernateInitialize.class);

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            try {
                registry = new StandardServiceRegistryBuilder().configure().build();

                MetadataSources sources = new MetadataSources(registry);

                Metadata metadata = sources.getMetadataBuilder().build();

                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                log.error(e.toString());
                if(registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if(registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}

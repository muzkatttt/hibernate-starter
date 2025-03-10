package com.muzkat;

import java.sql.SQLException;
import java.time.LocalDate;

import com.muzkat.entity.User;
import org.hibernate.Session;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

public class HibernateRunner {
    public static void main(String[] args) throws SQLException {
        //BlockingQueue<Connection> pool = null;

        // Connection --> SessionFactory
        //final Connection take = pool.take();

        // Connection --> Session
//        final Connection connection = DriverManager.getConnection("db.url",
//                "db.username", "db.password");
        Configuration configuration = new Configuration();
//        configuration.addAnnotatedClass(User.class);

        //используем setPhysicalNamingStrategy() для преобразования названий в классах CamelCase в snape_case в БД
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        configuration.configure();

        // как и ConnectionPool() должен существовать только один объект
        //типа SessionFactory на все приложение

        // sessionFactory.openSession() - обертка над Connection в JDBC
        try (var sessionFactory = configuration.buildSessionFactory();
              Session session = sessionFactory.openSession()) {

            session.beginTransaction();
            System.out.println("OK");

            User user = User.builder()
                    .userName("marcus@gmail.com")
                    .firstName("Mark")
                    .lastName("Gustav")
                    .birthDate(LocalDate.of(1990, 1, 10))
                    .age(25)
                    .build();
            session.persist(user);

            session.getTransaction().commit();

        }



    }
}

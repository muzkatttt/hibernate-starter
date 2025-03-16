package com.muzkat;

import com.muzkat.entity.Birthday;
import com.muzkat.entity.User;
import com.muzkat.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.time.LocalDate;


public class HibernateRunner {

    private static final Logger log = LoggerFactory.getLogger(HibernateRunner.class);
    public static void main(String[] args) throws SQLException {
        //BlockingQueue<Connection> pool = null;

        // Connection --> SessionFactory
        //final Connection take = pool.take();

        // Connection --> Session
//        final Connection connection = DriverManager.getConnection("db.url",
//                "db.username", "db.password");
//        Configuration configuration = new Configuration();
//        configuration.addAnnotatedClass(User.class);
//
//        //используем setPhysicalNamingStrategy() для преобразования названий в классах CamelCase в snape_case в БД
//        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
//        configuration.configure();
//
//        // чтобы не добавлять аннотацию для конвертера над каждым столбцом в сущностях
//        // можно добавить настройки в конфигурацию:
//        // первым аргументом указываем кастомный конвертор, второй аргумент true - и hibernate будет автоматически
//        // использовать для всех сущностей данный конвертер, если не указывать второй аргумент, то
//        // нужно будет добавлять аннотацию над столбцом
//        // либо над классом BirthdayConverter ставим аннотацию @Converter
//
////        configuration.addAttributeConverter(new BirthdayConverter(), true);
//        configuration.addAttributeConverter(new BirthdayConverter());
//
//
//        // как и ConnectionPool() должен существовать только один объект
//        //типа SessionFactory на все приложение
//
//        // sessionFactory.openSession() - обертка над Connection в JDBC
//        try (var sessionFactory = configuration.buildSessionFactory();
//              Session session = sessionFactory.openSession()) {
//
//            session.beginTransaction();
//            System.out.println("OK");
//
//            User user = User.builder()
//                    .userName("paryvag@gmail.com")
//                    .firstName("pary")
//                    .lastName("Vag")
//                    .birthDate(new Birthday(LocalDate.of(1999, 2, 10)))
//                    .role(1)
//                    .build();
//            session.persist(user);
//
//            session.getTransaction().commit();
//
//        }
//
//
//
//    }
        // этот код с уроков до состояний данных в Hibernate

//        Configuration configuration = new Configuration();
//
//        // Добавляем аннотированный класс User
//        configuration.addAnnotatedClass(User.class);
//
//        // Настройка преобразования имен (CamelCase → snake_case)
//        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
//
//        // Регистрируем Attribute Converter
//        configuration.addAttributeConverter(new BirthdayConverter());
//
//        // Подключение конфигурационного файла Hibernate
//        configuration.configure();
//
//        try (var sessionFactory = configuration.buildSessionFactory();
//             Session session = sessionFactory.openSession()) {
//
//            session.beginTransaction();
//
//            User user = User.builder()
//                    .userName("example@gmail.com")
//                    .firstName("First")
//                    .lastName("Last")
//                    .birthDate(new Birthday(LocalDate.of(1999, 2, 10)))
//                    .role(1)
//                    .build();
//
//            session.persist(user);
//
//            session.getTransaction().commit();
//
//            System.out.println("User saved successfully!");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
            // состояние transient
            User user = User.builder()
                    .userName("example@gmail.com")
                    .firstName("First")
                    .lastName("Last")
                    .birthDate(new Birthday(LocalDate.of(1999, 2, 10)))
                    .role(1)
                    .build();
            //оформление сообщений в логгере чрез {} - где каждая {} - это аргумент, т.е. полоьзуемся без конкатенаци
            log.info("User entity is transient state, object: {}", user);

        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             Session session1 = sessionFactory.openSession()) {

            Transaction transaction = session1.beginTransaction();
            log.trace("Transaction is created, {}", transaction);

            //состояние persistent
            session1.saveOrUpdate(user);
            log.trace("User is in persistent state: {}, session {}", user, session1);


            session1.getTransaction().commit();
            log.warn("User is detached state: {}, session is closed {}", user, session1);


        }
//        try (var sessionFactory = HibernateUtil.buildSessionFactory();
//             Session session2 = sessionFactory.openSession()) {
//
//            session2.beginTransaction();
//
//            // состояние removed к session2
//            //session2.delete(user);
//
//            // даже если в 135 строке мы поменяем поле,
//            // hibernate все равно сначала сделает запрос в БД, и в строке 139 выполнит refresh(user)
//            //user.setFirstName("Paul");
//
//            // в persist контексте будет извлечено значение user из БД
//            //session2.refresh(user);
//
//            // merge поступает по-другому: он возвращает новый объект, но меняет их на поля из persist
//            // т.е. меняет данные в БД на новые
//
//            session2.getTransaction().commit();
//        } catch (Exception e) {
//            //e.printStackTrace();
//            log.error("Exception accured", e);
//            throw e;
//        }
    }
}

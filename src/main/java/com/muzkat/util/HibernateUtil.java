package com.muzkat.util;

import com.muzkat.converter.BirthdayConverter;
import com.muzkat.entity.User;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {
    public static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();

        // Добавляем аннотированный класс User
        configuration.addAnnotatedClass(User.class);

        // Настройка преобразования имен (CamelCase → snake_case)
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());

        // Регистрируем Attribute Converter
        configuration.addAttributeConverter(new BirthdayConverter());

        // Подключение конфигурационного файла Hibernate
        configuration.configure();
        return configuration.buildSessionFactory();
    }
}

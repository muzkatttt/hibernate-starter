package com.muzkat;

import com.muzkat.entity.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class HibernateRunnerTest {

    /*
    @Test
    void checkRefflectionApi() throws SQLException, IllegalAccessException {
        User user = User.builder()
                .userName("largous@gmail.com")
                .firstName("Lev")
                .lastName("Gouroni")
                .birthDate(LocalDate.of(1998, 2, 10))
                .age(27)
                .build();

        String sql = """
                INSERT
                INTO
                %s
                (%s)
                VALUES
                (%s)
                """;
        //первая составляющая динамического запроса - первое значение - название таблицы
        String tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
                .map(tableAnnotation -> tableAnnotation.schema() + "." +
                                        tableAnnotation.name())
                .orElse(user.getClass().getName());


        //вторая составляющая динамического запроса - название столбца
        Field[] declaredFields = user.getClass().getDeclaredFields();

        String columnNames = Arrays.stream(declaredFields)
                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
                        .map(Column::name)
                        .orElse(field.getName()))
                .collect(joining(", "));


        // третья составляющая динамического запроса - значения столбцов
        String columnValues = Arrays.stream(declaredFields)
                .map(field -> "?")
                .collect(joining(", "));

        System.out.println(sql.formatted(tableName, columnNames, columnValues));

//        Connection connection = null;
//        PreparedStatement preparedStatement = connection.prepareStatement(sql.formatted(tableName, columnNames, columnValues));
//        for(Field declaredField : declaredFields){
//            declaredField.setAccessible(true);
//            preparedStatement.setObject(1,declaredField.get(user));
//        }
    }

     */

    void checkGetReflectionApi() throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.getString("username");
        resultSet.getString("firstnamme");
        resultSet.getString("lastname");
        resultSet.getDate("birth_date");

        Class<User> userClass = User.class;
        Constructor<User> constructor = userClass.getConstructor();
        User user = constructor.newInstance();
        Field usernameField = userClass.getDeclaredField("username");
        usernameField.setAccessible(true);
        usernameField.set(user, resultSet.getString("username"));

    }
}

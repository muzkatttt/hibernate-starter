package com.muzkat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User {

    @Id
    @Column(name = "username")
    private String userName;

    @Embedded
    private PersonalInfo personalInfo;

    @Column(name ="firstname")
    private String firstName;

    @Column(name ="lastname")
    private String lastName;

    //@Convert(converter= BirthdayConverter.class) // подключение конвертера
    @Column(name="birth_date")
    private Birthday birthDate;

//    @Column(name="age")
//    private Integer age;

    @Column(name = "role")
    private Integer role;

}

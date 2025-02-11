package com.student.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement
public class Student {

    private long id;
    private String firstName;
    private String surname;
    private String dept;
    private Double fees;
    private College college = new College(null, null, null, null);

    public Student(long id, String firstName, String dept, String surname, Double fees) {
        this.id = id;
        this.firstName = firstName;
        this.dept = dept;
        this.surname = surname;
        this.fees = fees;
    }
}
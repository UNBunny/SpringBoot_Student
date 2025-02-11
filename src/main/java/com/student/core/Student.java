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
    private College college;

}
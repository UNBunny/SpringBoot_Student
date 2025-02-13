package com.student.repository;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "person", types = Person.class)
public interface Person {
    String getFirstName();
    String getSurname();
    String getId();

}

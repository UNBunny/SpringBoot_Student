package com.student.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import com.student.core.College;
import com.student.core.Student;

@Named
public class StudentDaoImpl implements StudentDao {

    private Map<Long, Student> students;

    {
        students = new HashMap<>();
        students.put(1L, new Student(1L, "Eric", "Colbert", "English Literature", 145.00, new College("New York", "NYU", "NY", "Broadway")));
        students.put(2L, new Student(2L, "Mary", "Contrary", "Physics", 155.00, new College("Boston", "Harvard", "MA", "Mass Ave")));
        students.put(3L, new Student(3L, "Jason", "Stewart", "English Literature", 145.00, new College("New York", "NYU", "NY", "Broadway")));
        students.put(4L, new Student(4L, "Ester", "Freeman", "English Literature", 145.00, new College("Chicago", "UChicago", "IL", "Ellis Ave")));
        students.put(5L, new Student(5L, "Ann", "Mouvier", "French", 125.00, new College("Paris", "Sorbonne", "Île-de-France", "Rue des Écoles")));

    }

    @Override
    public Student getOne(long id) {
        return students.get(id);
    }

    @Override
    public Collection<Student> getAll() {
        return students.values();
    }

    @Override
    public void add(Student student) {
        long id = students.keySet().size();
        id++;
        student.setId(id);
        students.put(id, student);
    }

}

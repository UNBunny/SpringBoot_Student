package com.student.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class College {
    private String city;
    private String name;
    private String state;
    private String street;
}

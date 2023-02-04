package com.example.springmvcrud.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EmployeeDTO {

    private Long id;
    private String name;
    private double salary;
    private String department;

}

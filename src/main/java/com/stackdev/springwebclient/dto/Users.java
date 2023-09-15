package com.stackdev.springwebclient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private String id;
    private String name;
    private int age;
    private double salary;
    private String department;
}

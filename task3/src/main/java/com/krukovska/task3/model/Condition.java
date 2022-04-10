package com.krukovska.task3.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Condition {

    private String fieldName;
    private String value;
    private String operator;
}

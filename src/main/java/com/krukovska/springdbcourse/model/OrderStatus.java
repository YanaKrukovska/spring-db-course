package com.krukovska.springdbcourse.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderStatus {

    NEW("New"),
    PROCESSED("Processed"),
    IN_PROGRESS("In progress");

    private final String name;

}

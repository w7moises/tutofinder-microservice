package com.tutofinder.tutorship.util;

public enum ExceptionMessagesEnum {
    TUTORSHIP_NOT_FOUND("TutorShip Not Found"),
    REPORT_NOT_FOUND("Report Not Found"),
    COURSE_NOT_FOUND("Course Not Found"),
    STUDENT_NOT_FOUND("Student Not Found"),
    INCORRECT_REQUEST_EMPTY_ITEMS_ORDER("Empty Items are not allowed in the Order");

    ExceptionMessagesEnum(String msg) {
        value = msg;
    }

    private final String value;

    public String getValue(){
        return value;
    }
}
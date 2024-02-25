package com.github.cigor99.resulter;

import org.springframework.http.HttpStatus;

public class ResultType {
    public static ResultType INVALID = new ResultType("INVALID");
    public static ResultType NOT_FOUND = new ResultType("NOT_FOUND");
    public static ResultType CONFLICT = new ResultType("CONFLICT");
    public static ResultType SUCCESS = new ResultType("SUCCESS");
    public static ResultType CREATED = new ResultType("CREATED");
    public static ResultType UNAUTHORIZED = new ResultType("UNAUTHORIZED");
    public static ResultType FORBIDDEN = new ResultType("FORBIDDEN");
    public static ResultType INTERNAL_SERVER_ERROR = new ResultType("INTERNAL_SERVER_ERROR");

    private final String name;

    public String name() {
        return this.name;
    }

    private ResultType(String name) {
        this.name = name;
    }

    public HttpStatus mapToHttpStatus() {
        return switch (this.name) {
            case "INVALID" -> HttpStatus.BAD_REQUEST;
            case "NOT_FOUND" -> HttpStatus.NOT_FOUND;
            case "CONFLICT" -> HttpStatus.CONFLICT;
            case "SUCCESS" -> HttpStatus.OK;
            case "CREATED" -> HttpStatus.CREATED;
            case "UNAUTHORIZED" -> HttpStatus.UNAUTHORIZED;
            case "FORBIDDEN" -> HttpStatus.FORBIDDEN;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}

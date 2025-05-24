package com.lspt.Travels_BE.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNCASEGORIZED_EXCEPTION(9999,"Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Invalid message key", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002,"User existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Email must be at least 3 characters",HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Password must be at least 6 characters",HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005,"User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006,"Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007,"You do not have permission", HttpStatus.FORBIDDEN),
    PHONE_INVALID(1008, "Phone number is invalid",HttpStatus.BAD_REQUEST),
    FULLNAME_INVALID(1009, "FullName must be at least 6 characters",HttpStatus.BAD_REQUEST),
    DOB_INVALID(1010, "Age must be greater than or equal to 18",HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR(1011, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR)
    ;

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

}
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
    OLD_PASSWORD_ERROR(1016, "Old password is incorrect",HttpStatus.BAD_REQUEST),
    NEW_AS_OLD_PASSWORD(1012, "new password same as old password",HttpStatus.BAD_REQUEST),
    NAMETOURTYPE_EXISTED(1013, "Name tour type already existed",HttpStatus.BAD_REQUEST),
    DESTINATIONNAME_EXISTED(1014, "destination name already existed",HttpStatus.BAD_REQUEST),
    DOB_INVALID(1010, "Age must be greater than or equal to 18",HttpStatus.BAD_REQUEST),
<<<<<<< HEAD
    INTERNAL_SERVER_ERROR(1011, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    TOUR_NAME_INVALID(2012, "Tour name is invalid", HttpStatus.BAD_REQUEST),
    TOUR_DESCRIPTION_INVALID(2013, "Tour description is invalid", HttpStatus.BAD_REQUEST),
    TOUR_PRICE_INVALID(2014, "Tour price must be positive", HttpStatus.BAD_REQUEST),
    TOUR_DEPARTURE_DATE_INVALID(2015, "Departure date must be in the future", HttpStatus.BAD_REQUEST),
    TOUR_DURATION_INVALID(2016, "Tour duration is invalid", HttpStatus.BAD_REQUEST),
    TOUR_LOCATION_INVALID(2017, "Departure location is required", HttpStatus.BAD_REQUEST),
    TOUR_EXISTED(1018,"Tour existed", HttpStatus.BAD_REQUEST),
    TOUR_NOT_EXISTED(1019,"Tour not existed", HttpStatus.NOT_FOUND),
    TOURTYPE_EXISTED(1020,"TourType existed", HttpStatus.BAD_REQUEST),
    TOURTYPE_NOT_EXISTED(1021,"TourType not existed", HttpStatus.NOT_FOUND),
=======
    INTERNAL_SERVER_ERROR(1011, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR)

>>>>>>> 514b2896ae055d8306855790cfab99ad5aacc892
    ;

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

}
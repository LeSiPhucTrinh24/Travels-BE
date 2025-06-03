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
    OLD_PASSWORD_ERROR(1010, "Old password is incorrect",HttpStatus.BAD_REQUEST),
    NEW_AS_OLD_PASSWORD(1011, "new password same as old password",HttpStatus.BAD_REQUEST),
    NAMETOURTYPE_EXISTED(1012, "Name tour type already existed",HttpStatus.BAD_REQUEST),
    DESTINATIONNAME_EXISTED(1013, "destination name already existed",HttpStatus.BAD_REQUEST),
    DOB_INVALID(1014, "Age must be greater than or equal to 18",HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR(1015, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    TOUR_NAME_INVALID(2016, "Tour name is invalid", HttpStatus.BAD_REQUEST),
    TOUR_DESCRIPTION_INVALID(2017, "Tour description is invalid", HttpStatus.BAD_REQUEST),
    TOUR_PRICE_INVALID(2018, "Tour price must be positive", HttpStatus.BAD_REQUEST),
    TOUR_DEPARTURE_DATE_INVALID(2019, "Departure date must be in the future", HttpStatus.BAD_REQUEST),
    TOUR_DURATION_INVALID(2020, "Tour duration is invalid", HttpStatus.BAD_REQUEST),
    TOUR_LOCATION_INVALID(2021, "Departure location is required", HttpStatus.BAD_REQUEST),
    TOUR_EXISTED(1022,"Tour existed", HttpStatus.BAD_REQUEST),
    TOUR_NOT_EXISTED(1023,"Tour not existed", HttpStatus.NOT_FOUND),
    TOURTYPE_EXISTED(1024,"TourType existed", HttpStatus.BAD_REQUEST),
    TOURTYPE_NOT_EXISTED(1025,"TourType not existed", HttpStatus.NOT_FOUND),
    BOOKING_NOT_EXISTED(1026,"Booking not existed", HttpStatus.NOT_FOUND),
    TOURIMAGE_NOT_EXISTED(1027,"TourImage not existed", HttpStatus.NOT_FOUND),
    INVALID_FILE(1028, "INVALID_FILE", HttpStatus.BAD_REQUEST),
    DESTINATION_NOT_EXISTED(1029, "Destination not existed", HttpStatus.NOT_FOUND),
    ITINERARY_NOT_EXISTED(1030,"Itinerary not existed", HttpStatus.NOT_FOUND),
    ;

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

}
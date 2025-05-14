package com.lspt.Travels_BE.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNCASEGORIZED_EXCEPTION(9999,"Loi chua phan loai"),
    INVALIID_KEY(1001, "Khong tim thay khoa dang ky"),
    USER_EXISTED(1002,"Nguoi dung da ton tai"),
    USERNAME_INVALID(1003, "Ho ten phai lon hon 5 ky tu"),
    INVALID_PASSWORD(1004, "Mat khau phai lon hon 8 ky tu")
    ;

    private final int code;
    private final String message;

}
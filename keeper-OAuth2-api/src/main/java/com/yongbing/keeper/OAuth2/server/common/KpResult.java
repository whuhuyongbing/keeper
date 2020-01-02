package com.yongbing.keeper.OAuth2.server.common;


import lombok.Data;

import javax.persistence.Entity;

/**
 * @author Yongbing Hu
 * @version 0.0.0
 * @time 2019-12-29 4:17 p.m.
 * @description
 */
@Data
@Entity
public class KpResult {

    private int status;
    private int errorCode;
    public static int NO_ERROR = 0;
    public static int PASSWORD_ERROR = 1;
    public static int USER_NOT_EXIST = 2;
    public static int USER_ALREADY_EXIST = 3;
    /**
     *
     */

    private String message;

    KpResult(int status, int errorCode, String message) {
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
    }

    public static KpResult createKpResult(int status, int errorCode,  String message) {
        return new KpResult(status, errorCode, message);
    }

}

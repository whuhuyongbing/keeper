package com.yongbing.keeper.common;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @author Yongbing Hu
 * @version 0.0.0
 * @description
 */
@Data
@Entity
public class KpResult implements Serializable {

    private int status;
    private String message;
    private int errorCode;


    KpResult(int status, int errorCode, String message) {
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
    }

    public static KpResult createResult(int status, int errorCode, String message) {
        return new KpResult(status, errorCode, message);
    }
}

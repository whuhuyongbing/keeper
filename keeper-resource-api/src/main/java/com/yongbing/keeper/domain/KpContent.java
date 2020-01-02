package com.yongbing.keeper.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Yongbing Hu
 * @version 0.0.0
 * @description
 */
@Data
@Entity
public class KpContent implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue
    private Long id;

    private Long userID;
    private Date date;
    private String title;
    private String content;
}

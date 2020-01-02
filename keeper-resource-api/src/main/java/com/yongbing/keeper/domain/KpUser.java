package com.yongbing.keeper.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Yongbing Hu
 * @version 0.0.0
 * @description
 */
@Entity
@Data
public class KpUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String email;

}

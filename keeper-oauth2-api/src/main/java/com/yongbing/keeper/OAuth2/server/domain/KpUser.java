package com.yongbing.keeper.OAuth2.server.domain;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @author Yongbing Hu
 * @version 0.0.0
 * @time 2019-12-29 2:16 p.m.
 * @description
 */

@Data
@Entity
public class KpUser implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String password;

    private String email;

}

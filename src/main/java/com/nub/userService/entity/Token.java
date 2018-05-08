package com.nub.userService.entity;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tokens")
public class Token {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String token;

    @Column(unique = true, nullable = false)
    private String iduser;

    @Column(nullable = false)
    private Date creationDate;

    @Column(nullable = false)
    private String expirationDate;

}

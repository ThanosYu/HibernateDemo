package com.thanos.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Thanos Yu
 * @date 10/9/2018 1:27 PM
 */
@Entity
@Table(name = "user")
public class User implements Serializable{

    /**
     * 地址，唯一的
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 名称
     */
    @Column(name = "password")
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
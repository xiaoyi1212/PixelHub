package com.pixelecraft.pixelhub.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.lang.annotation.Annotation;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Entity
@Getter
@Table(name = "daouser")
public class DaoUser{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "username")
    private String username;
    @Column(name = "imgt")
    private String imgt;

    public void setImgt(String img) {
        this.imgt = imgt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getUuid() {
        return UUID.nameUUIDFromBytes((email + password).getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String toString() {
        return "DaoUser:{"+username+","+email+","+password+"}";
    }
}

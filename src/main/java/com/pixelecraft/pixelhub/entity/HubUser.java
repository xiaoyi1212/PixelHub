package com.pixelecraft.pixelhub.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class HubUser {

    @Getter
    @Setter
    String username, email, password;

    @Getter
    @Setter
    UUID uuid;

    public HubUser() {

    }
}

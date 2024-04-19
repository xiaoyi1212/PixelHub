package com.pixelecraft.pixelhub.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class AccessKey {
    @Getter
    @Setter
    int time;

    @Getter
    @Setter
    LoginUser user;

    @Getter
    @Setter
    String key;

    @Getter
    @Setter
    UUID uuid;

    public AccessKey(LoginUser user,int time,UUID uuid,String key){
        this.user = user;
        this.time = time;
        this.uuid = uuid;
        this.key = key;
    }

    public void subTime(){
        this.time--;
    }
}

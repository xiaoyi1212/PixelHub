package com.pixelecraft.pixelhub.service;

import com.pixelecraft.pixelhub.entity.AccessKey;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Service
public class TimeService {
    Set<AccessKey> keys = new CopyOnWriteArraySet<>();

    public void addKey(AccessKey key){
        this.keys.add(key);
    }

    public Set<AccessKey> getKeys(){
        return keys;
    }

    @Scheduled(fixedDelay = 1000)
    public void execute(){
        for (Iterator<AccessKey> iterator = keys.iterator(); iterator.hasNext(); ) {
            AccessKey key = iterator.next();
            key.subTime();
            if(key.getTime() == 0) iterator.remove();
        }
    }
}

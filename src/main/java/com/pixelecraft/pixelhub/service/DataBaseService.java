package com.pixelecraft.pixelhub.service;

import com.pixelecraft.pixelhub.dao.DaoMapper;
import com.pixelecraft.pixelhub.entity.DaoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataBaseService {

    static int id = 0;

    @Autowired
    DaoMapper mapper;

    public DaoUser getUser(String email) {
        List<DaoUser> users = mapper.findAll();
        for (DaoUser user : users) {
            if (user.getEmail().equals(email)) return user;
        }
        return null;
    }

    public void save(DaoUser user){
        mapper.save(user);
    }

    public DaoUser update(DaoUser user) {
        return mapper.save(user);
    }

    public DaoUser delete(Long id) {
        DaoUser user = mapper.findById(id).get();
        mapper.delete(user);
        return user;
    }

    public DaoUser findById(Long id) {
        return mapper.findById(id).get();
    }
}

package com.pixelecraft.pixelhub.dao;

import com.pixelecraft.pixelhub.entity.DaoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoMapper extends JpaRepository<DaoUser,Long> {
}

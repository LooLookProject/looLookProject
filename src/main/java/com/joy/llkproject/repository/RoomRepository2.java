package com.joy.llkproject.repository;

import com.joy.llkproject.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository2 extends JpaRepository<Room,String> {

    @Query(value=
            "select * from hpp_room r " +
                    "join r.user u " +
                    "where u.userId= :userId", nativeQuery = true)
    List<Room> getRoomByUserId(@Param("userId") String userId);
}

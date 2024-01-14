package com.joy.llkproject.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserForm {
    // user
    String userId;
    String userPw;
    String userName;
    String address;

    Integer floor;
    Integer roomCount;
    Integer roomNumber;
    String roomType;
    // room
    List<RoomForm> roomList;

}

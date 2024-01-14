package com.joy.llkproject.entity;

import lombok.Data;

import java.util.List;

@Data
public class FormEntity {
    User user;
    List<Room> roomList;
}
